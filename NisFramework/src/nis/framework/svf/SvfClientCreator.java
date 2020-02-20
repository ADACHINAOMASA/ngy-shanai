package nis.framework.svf;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SvfClientCreator {

	/**
	 * 通常運用サーバー、バックアップサーバーの切り替えを加味したSVFクライアントの生成
	 */
	public static <R extends SvfAdapter> R create(String tsujoServer, String backupServer, Class<R> clientClass) throws PrintException {
		// 通常運用サーバー
		try {
			try {
				// Resolve
				InetAddress address = InetAddress.getByName(tsujoServer.toLowerCase());
				// Echo
				try {
					if (address.isReachable(2 * 1000)) {
						// 2秒以内にEcho応答あり
					}
					else {
						throw new PrintException("通常運用サーバーへのEcho失敗");
					}
				}
				catch (IOException e) {
					// 通常運用サーバーへのEchoコマンドの失敗
					throw new PrintException(e);
				}
				// クライアント生成
				return newInstance(clientClass, tsujoServer);
			}
			catch (UnknownHostException e) {
				// 通常運用サーバーが見つからない
				throw new PrintException(e);
			}
		}
		catch (PrintException e) {
			// SVFがインストールされてーるサーバーは立ち上がっているが、SVFサーバーサービスが起動していない場合は数秒後にここにくる
			// スルーする
		}

		// 通常運用サーバー
		return newInstance(clientClass, backupServer);
	}

	/**
	 * インスタンス生成
	 *
	 * 	 SvfrClient(hostName)により生成
	 *
	 *   svfはこのコンストラクターでSVFサーバーサービスとのコネクションまで確立しているようだ。
	 *
	 */
	private static <R extends SvfAdapter> R newInstance(Class<R> clientClass, String server) throws PrintException {
		try {
			Constructor<R> constructor = clientClass.getConstructor(String.class);
			return constructor.newInstance(server);
		}
		catch (SecurityException e) {
			throw new PrintException(e);
		}
		catch (NoSuchMethodException e) {
			throw new PrintException(e);
		}
		catch (IllegalArgumentException e) {
			throw new PrintException(e);
		}
		catch (InstantiationException e) {
			throw new PrintException(e);
		}
		catch (IllegalAccessException e) {
			throw new PrintException(e);
		}
		catch (InvocationTargetException e) {
			throw new PrintException(e);
		}
	}

}
