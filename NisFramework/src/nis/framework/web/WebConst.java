package nis.framework.web;

/**
 * 定数を書き込むクラスです。
 */
public final class WebConst {

	/**
	 * アクションフォワード定数を書き込むクラスです。
	 */
	public final class Forward {
		/** 成功 */
		public final static String SUCCESS = "success";
		/** 失敗 */
		public final static String FAILED = "failed";
		/** 致命的なエラー */
		public final static String ERROR = "fatal";
		/** 不正な呼び出し */
		public final static String INVALID_ORDER = "invalid_order";
		/** セッションタイムアウト */
		public final static String SESSION_TIME_OUT = "time_out";
	}

	/**
	 * オブジェクトを保持するためのキー定数を書き込むクラスです。
	 */
	public final class ObjectKey {

		/** アプリケーションオブジェクト */
		public final static String APPLICATION = "scope.application.key.application";
		/** コンボボックス用コンテナ */
		public final static String COMBO_BOX_CONTAINER = "scope.application.key.combo_box_container";
		/** ページ内行数 */
		public final static String NUMBER_OF_LINES = "scope.application.key.number_of_lines";
		/** セッションオブジェクト */
		public final static String SESSION = "scope.session.key.session";
		/** パブリック情報 */
		public final static String USER_PROFILE = "scope.session.key.user_profile";
		/** 業務情報 */
		public final static String EJBTRANSACTION_INFORMATION = "scope.session.key.ejbtransaction_information";
		/** 印刷情報 */
		public final static String PDF_INFORMATION = "scope.session.key.pdf_information";
		/** エクセル情報 */
		public final static String EXCEL_INFORMATION = "scope.session.key.excel_information";
		/** 完成されたメッセージ */
		public final static String INTACT_MESSAGES = "scope.request.key.intact_messages";
		/** アナウンス */
		public final static String ANNOUNCE = "scope.request.key.announce";
		/** スローされた例外 */
		public final static String FATAL_EXCEPTION = "scope.request.key.fatal_exception";
		/** フロントID */
		public final static String FRONT_ID = "scope.request.key.front_id";
		/** トランザクションモデル */
		public final static String TRANSACTION_INFORMATION = "business_information.key.transaction_information";

		/** セッションコンボボックス用コンテナ */
		public static final String SESSION_COMBO_BOX_CONTAINER ="scope.session.key.combo_box_container";

		/** 初期表示会社クッキーキー */
		public static final String COOKIE_KEY_DEFAULT_KAISHA = "userprefs.default_kaisha";
		/** 初期表示ボードクッキーキー */
		public static final String COOKIE_KEY_DEFAULT_BOARD = "userprefs.default_board";

		/** 社員情報セッションキー */
		public static final String SHAIN_SESSION_KEY = "shain";

		/** ユーザパスワード暗号化キー */
		public static final String USER_ENCRYPT_KEY = "bRgYD8benWiB4iMr9hFHLC7QHanpSDwxzJWRriYYHkyVLnmVb3";
	}

	/**
	 * フォーマットパターン定数を書き込むクラスです。
	 */
	public final class Format {
		// 年月日
		public final static String YMD = "yyyy/MM/dd";
		// 年月
		public final static String YM = "yyyy/MM";
		// 日
		public final static String DAY = "dd";
		// 時分
		public final static String HM = "HH:mm";
		// 時分秒
		public final static String HMS = "HH:mm:ss";
	}

	/**
	 * デフォルト値の定数を書き込むクラスです。
	 */
	public final class Default {
		/** ページ内行数 */
		public final static int NUMBER_OF_LINES = 10;
		/** 履歴件数 */
		public final static int NUMBER_OF_HISTORY = 50;
		/** ページコントロール部品の格納プロパティ */
		public final static String PAGE_CONTROL = "pageControl";
		/** 送信ボタン名 */
		public final static String SUBMIT_NAME = "submitbutton";
		/** 標準メッセージ格納プロパティ */
		public final static String MESSAGE_PROPERTY = "messages.common_alert";
		/** ボード表示の変更のための閾値 */
		public final static int OVER_BOARD_VAL = 5;
		/** ホワイトボードお知らせ基本表示数 */
		public final static int BASE_SHOW_OSHIRASE_VALUE = 2;
		/** ホワイトボードお知らせが空の場合の表示文字列 */
		public final static String STR_OSHIRASE_EMPTY = "　";
		/** 他社の会社コード */
		public static final String TASHA_KAISHA_CD = "AAA";
		/** リンク文字列 */
		public static final String LINK_STR = "リンク";
	}

}
