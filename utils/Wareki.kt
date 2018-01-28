package jp.liang.tanuki.utils

import java.util.Calendar


/**
 * Created by liang_hu on 西暦17/11/29.
 * 和暦変換ツール
 */
object Wareki {
    fun parse(dateInt: Int): Pair<String, Int> {
        val yearInt = dateInt/10000
        return when(dateInt){
            in 18680908..19120729 -> Pair("明治", yearInt-1868)
            in 19120730..19261224 -> Pair("大正", yearInt-1912)
            in 19261225..19890107 -> Pair("昭和", yearInt-1926)
            else -> Pair("平成", yearInt-1989)
        }
    }

    /**
     * 日付を和暦表示にする
     *
     * e.g
     *
     * toJapaneseCalendar(Calendar.getInstance()) -> 平成29年11月29日
     *
     */
    fun toJapaneseCalendar(calendar: Calendar): String {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTHの数字は0から
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val (era, yearNo) = parse(year * 10000 + month * 100 + dayOfMonth)

        val yearText:String = if(yearNo==0) "元" else "${yearNo+1}"
        return "${era}${yearText}年${month}月${dayOfMonth}日"
    }
}