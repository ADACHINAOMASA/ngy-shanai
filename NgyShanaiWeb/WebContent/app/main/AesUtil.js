/**
 * AesUtil.javaと同じ
 */
var AesUtil = (function() {

	var DEFAULT_KEY_SIZE = 128;
	var DEFAULT_ITERATION = 1000;

	function generateKey(salt, passPhrase) {
		var key = CryptoJS.PBKDF2(passPhrase, CryptoJS.enc.Hex.parse(salt), {
			keySize : DEFAULT_KEY_SIZE / 32,
			iterations : DEFAULT_ITERATION
		});
		return key;
	}

	function encrypt(salt, iv, passPhrase, plainText) {
		var key = generateKey(salt, passPhrase);
		var encrypted = CryptoJS.AES.encrypt(plainText, key, {
			iv : CryptoJS.enc.Hex.parse(iv)
		});
		return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
	}

	/*
	function encrypt2(plainText, key, iv) {
		var encrypted = CryptoJS.AES.encrypt(plainText, key, {
			iv : CryptoJS.enc.Hex.parse(iv)
		});
		return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
	}
	*/

	function decrypt(salt, iv, passPhrase, cipherText) {
		var key = generateKey(salt, passPhrase);
		var cipherParams = CryptoJS.lib.CipherParams.create({
			ciphertext : CryptoJS.enc.Base64.parse(cipherText)
		});
		var decrypted = CryptoJS.AES.decrypt(cipherParams, key, {
			iv : CryptoJS.enc.Hex.parse(iv)
		});
		return decrypted.toString(CryptoJS.enc.Utf8);
	}

	/*
	function decrypt2(cipherText, key, iv) {
		var cipherParams = CryptoJS.lib.CipherParams.create({
			ciphertext : CryptoJS.enc.Base64.parse(cipherText)
		});
		var decrypted = CryptoJS.AES.decrypt(cipherParams, key, {
			iv : CryptoJS.enc.Hex.parse(iv)
		});
		return decrypted.toString(CryptoJS.enc.Utf8);
	}
	*/

	return {
		generateKey : generateKey,
		encrypt : encrypt,
		//encrypt2 : encrypt2,
		decrypt : decrypt,
		//decrypt2 : decrypt2,
	}

})();