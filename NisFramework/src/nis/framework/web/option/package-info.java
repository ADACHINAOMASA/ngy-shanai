/**
 * <p>
 * オプション（選択リスト、プルダウン、コンボ、ラジオ）のデータ構造及び生成用クラス群<br>
 * オプションという名称はHTMLのoptionタグと対応する為<br>
 * 生成方式については3パターンを用意<br>
 * ・EnumにKbnEnumインターフェースを継承し、SelectOptionsタグを付与<br>
 *   →列挙型のメンバを元にオプション群を生成<br>
 * ・Optionsを戻り値として持つメソッドにSelectOptionsタグを付与<br>
 *   →戻り値のOptionsをそのまま追加する。引数としてlogicExecutorを渡す事も出来る<br>
 * ・SelectOptionsインターフェースを実装する<br>
 *   →戻り値のOptionsをそのまま追加する。前述2つの方法で対応出来ないような大掛かりなものへの使用を想定<br>
 * 生成は基本的に解析で行われるので、コンテナに手動で追加する必要はない（手動で追加する事も出来る）<br>
 * TODO:オプションに対して複数の値を持たせられるようにするか
 * </p>
 * @author Kengo-Nagase
 */
package nis.framework.web.option;