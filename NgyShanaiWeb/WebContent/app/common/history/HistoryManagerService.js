/**
 * ヒストリー制御
 * HTML5の History API とは全く別の自前の履歴制御
 * HistoryAPIを使っていない（＝メモリ上にしか展開されていない）ので、リロードで初期化される
 * その状態で履歴への遷移が起きた場合は強制的にホーム画面へ飛ばす
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['$location'];

    // 引数は依存の内容と一致する
    var newService = function ($location) {

        var _HOME_URL = '/home';

        /**
         * パス内にクエリを含む為に作った履歴制御なので、一般的な制御と挙動が異なる点が多い
         * ステート開始時に、開始時のURLを履歴に追加、
         * ステート終了時に、終了時のURLを履歴に上書き
         * 戻る時には、戻った地点より先の履歴を消し、戻る地点の履歴には触らない
         */

        var _histories = [];

        return {
            /**
             * 履歴の追加（遷移は伴わない）
             * 最後の履歴が同じステートだった場合、追加ではなく上書きにする
             * @param {stateName} 追加するstateの名前
             * @param {url} 追加するstateに対応するurl
             */
            push : function(stateName, url) {
                // ホームに戻った場合は履歴も最初から
                if (_HOME_URL === url) {
                    _histories = [];
                }
                // 最後のstateが同stateだった場合、stateは追加せず、urlだけ上書き
                // QueryParam等だけが異なった場合の分岐
                if (_histories.length && _.last(_histories).stateName === stateName) {
                    _.last(_histories).url = url;
                }
                else {
                    _histories.push({
                        stateName : stateName,
                        url : url
                    });
                }
            }
            /**
             * 最後の履歴が同じステートだった場合に限り、そのステートを上書きする
             * @param {stateName} 追加するstateの名前
             * @param {url} 追加するstateに対応するurl
             */
            , lastUpdate : function(stateName, url) {
                if (_histories.length && _.last(_histories).stateName === stateName) {
                    _.last(_histories).url = url;
                }
            }
            /**
             * 一つ前に戻る（遷移を伴う）
             * 履歴がない場合はホームに戻る
             */
            , back : function() {
                _histories.pop();
                if (!_histories.length) {
                    $location.url(_HOME_URL);
                    return;
                }
                $location.url(_.last(_histories).url);
            }
            /**
             * 指定したステートが履歴に存在するなら、その最後のものへ遷移する
             * @param {stateName} 移動したいstateの名前 配列で複数渡す事も出来る
             * @param {defaultState} 指定したstateが履歴に存在しない場合の移動先 省略時はホームに
             */
            , last : function(stateName, defaultState) {
                var lastState = null;
                var stateNames = Array.isArray(stateName) ? stateName : [stateName];
                var defaultState = defaultState || _HOME_URL;
                for (var idx = _histories.length - 1;idx >= 0;idx--) {
                    if (stateNames.indexOf(_histories[idx].stateName) >= 0) {
                        lastState = _histories[idx];
                        _histories.splice(idx + 1, (_histories.length - (idx + 1)));
                        break;
                    }
                }
                if (!lastState) {
                    // TODO:stateに移動する？
                    $location.url(defaultState);
                    return;
                }
                $location.url(_.last(_histories).url);
            }
            /**
             * 移動を伴わないで履歴を一つ破棄する
             * @returns {Object} 破棄された履歴
             */
            , pop : function() {
                return _histories.pop();
            }
            /**
             * 履歴のクリア
             */
            , clear : function() {
                _histories = [];
            }
        };

    };

    newService.$inject = injectParams;

    angular.module('app')
        // factory | service | provider
        .factory('HistoryManagerService', newService);

}());