package lotdsp.common.util;

import java.math.BigDecimal;

public class KeisanUtil {

	public static final BigDecimal ONE_MILLION = new BigDecimal(1000000);

	public static BigDecimal sum(BigDecimal... values) {
		BigDecimal total = BigDecimal.ZERO;
		for (BigDecimal val : values) {
			total = add(total, val);
		}
		return total;
	}

	public static BigDecimal absMinus(BigDecimal val) {
		if (NumberUtil.isZero(val)) {
			return BigDecimal.ZERO;
		}
		return NumberUtil.nvl(val).abs().multiply(new BigDecimal(-1));
	}

	public static BigDecimal add(BigDecimal val1, BigDecimal val2) {
		return NumberUtil.nvl(val1).add(NumberUtil.nvl(val2));
	}


	public static BigDecimal substract(BigDecimal val1, BigDecimal val2) {
		return NumberUtil.nvl(val1).subtract(NumberUtil.nvl(val2));
	}

	public static BigDecimal multiply(BigDecimal val1, BigDecimal val2) {
		return NumberUtil.nvl(val1).multiply(NumberUtil.nvl(val2));
	}

	public static BigDecimal multiply(BigDecimal val1, BigDecimal val2, int scale, int roundingMode) {
		return NumberUtil.nvl(val1).multiply(NumberUtil.nvl(val2)).setScale(scale, roundingMode);
	}

	public static BigDecimal setScale(BigDecimal val1, int scale) {
		return NumberUtil.nvl(val1).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal setScale(BigDecimal val, int scale,int rm) {
		return NumberUtil.nvl(val).setScale(scale, rm);
	}

	public static BigDecimal divide(BigDecimal val1, BigDecimal val2, int scale) {
		if (NumberUtil.isZero(val2)) {
			return BigDecimal.ZERO;
		}
		return NumberUtil.nvl(val1).divide(NumberUtil.nvl(val2), scale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal devideHundred(BigDecimal percentage) {
		return NumberUtil.nvl(percentage).divide(new BigDecimal(100));
	}

	// 金額
	public static BigDecimal kingakuDefault(BigDecimal suryo, BigDecimal tanka) {
		return kingaku(suryo, tanka).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal kingaku(BigDecimal suryo, BigDecimal tanka, int scale, int round) {
		return kingaku(suryo, tanka).setScale(scale, round);
	}

	public static BigDecimal kingaku(BigDecimal suryo, BigDecimal tanka) {
		return NumberUtil.nvl(suryo).multiply(NumberUtil.nvl(tanka));
	}

	// 金額（掛け率）
	public static BigDecimal kingakuWithKakeristuDefault(BigDecimal kingaku, BigDecimal kakeritsu) {
		return kingakuWithKakeristu(kingaku, kakeritsu).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal kingakuWithKakeristu(BigDecimal kingaku, BigDecimal kakeritsu, int scale, int round) {
		return kingakuWithKakeristu(kingaku, kakeritsu).setScale(scale, round);
	}

	public static BigDecimal kingakuWithKakeristu(BigDecimal kingaku, BigDecimal kakeritsu) {
		return NumberUtil.nvl(kingaku).multiply(NumberUtil.nvl(kakeritsu));
	}

	// 利益率
	public static BigDecimal rirekiRitsu(BigDecimal gokeiKingaku, BigDecimal kingaku) {
		if (NumberUtil.isZero(gokeiKingaku)) {
			return BigDecimal.valueOf(0);
		} else {
			return NumberUtil.nvl(kingaku).divide(NumberUtil.nvl(gokeiKingaku), 9, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(100));
		}
	}

	public static BigDecimal rirekiRitsu(BigDecimal gokeiKingaku, BigDecimal kingaku, int scale, int round) {
		return rirekiRitsu(gokeiKingaku, kingaku).setScale(scale, round);
	}

	public static BigDecimal rirekiRitsuDefault(BigDecimal gokeiKingaku, BigDecimal kingaku) {
		return rirekiRitsu(gokeiKingaku, kingaku).setScale(1, BigDecimal.ROUND_HALF_UP);
	}

	// 税額
	public static BigDecimal zeigaku(BigDecimal kingaku, BigDecimal keisu) {
		return NumberUtil.nvl(kingaku).multiply(keisu);
	}

	// 金額（税込）
	public static BigDecimal zeikomigaku(BigDecimal kingaku, BigDecimal keisu) {
		// 見積合計金額（税抜）＋見積合計金額（税抜）＊消費税
		BigDecimal zeigaku = zeigaku(kingaku, keisu);
		return add(kingaku, zeigaku);
	}

	public static BigDecimal multiplyAll(BigDecimal... values) {
		BigDecimal result = BigDecimal.ONE;
		for (BigDecimal val : values) {
			result = multiply(result, NumberUtil.nvl(val));
		}
		return result;
	}
	
}
