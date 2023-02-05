object CheckNationalCodeIranian {

    private val notNationalCode = arrayOf(
        "0000000000",
        "1111111111",
        "2222222222",
        "3333333333",
        "4444444444",
        "5555555555",
        "6666666666",
        "7777777777",
        "8888888888",
        "9999999999"
    )

   private fun convertPersianNumberToEnglish(stringNumber: String): String {
        var strNumber = stringNumber
        val persianNumber = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
        strNumber = strNumber.replace(persianNumber[0], "0")
        strNumber = strNumber.replace(persianNumber[1], "1")
        strNumber = strNumber.replace(persianNumber[2], "2")
        strNumber = strNumber.replace(persianNumber[3], "3")
        strNumber = strNumber.replace(persianNumber[4], "4")
        strNumber = strNumber.replace(persianNumber[5], "5")
        strNumber = strNumber.replace(persianNumber[6], "6")
        strNumber = strNumber.replace(persianNumber[7], "7")
        strNumber = strNumber.replace(persianNumber[8], "8")
        strNumber = strNumber.replace(persianNumber[9], "9")

        return strNumber
    }

    fun checkNationalCode(nationalCode: String?): Boolean {
        val nationalCodeEn = nationalCode?.let { convertPersianNumberToEnglish(it) } ?: return false

        if (nationalCodeEn.isEmpty()) {
            return false
        }
        if (nationalCodeEn == ""){
            return false
        }
        if (nationalCodeEn.length != 10) {
            return false
        }
        if (!nationalCodeEn.matches("[0-9]+".toRegex())) {
            return false
        }
        for (s in notNationalCode) {
            if (s.equals(nationalCodeEn, ignoreCase = true)) {
                return false
            }
        }
        val nationalCodeWithoutControlDigit = nationalCodeEn.substring(0, nationalCodeEn.length - 1)
        val controlDigit = nationalCodeEn.substring(nationalCodeEn.length - 1)
        var sum = 0
        var i = 10
        for (c in nationalCodeWithoutControlDigit.toCharArray()) {
            val temp = ("" + c).toInt() * i
            i--
            sum += temp
        }
        val modBy11 = sum % 11
        return if (modBy11 < 2) {
            modBy11 == controlDigit.toInt()
        } else 11 - modBy11 == controlDigit.toInt()
    }
}