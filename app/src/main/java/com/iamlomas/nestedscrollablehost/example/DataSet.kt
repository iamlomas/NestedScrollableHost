package com.iamlomas.nestedscrollablehost_sample

import com.iamlomas.nestedscrollablehost.example.R

object DataSet {

    val tabList = listOf("Nested Scrollables", "Shinobi List")

    const val NUMBER_OF_TABS = 2

    const val FIRST_TAB = 0

    const val SECOND_TAB = 1

    val imageList = listOf(
        R.drawable.hashirama_senju_by_keren_kekuatan,
        R.drawable.senju_hasirama_wallpaper,
        R.drawable.hashirama_modo_sabio,
        R.drawable.hashirama_laughter,
        R.drawable.naruto_hashirama_senju,
        R.drawable.namikaze_minato_konoha_jonin,
        R.drawable.hatake_kakashi_copy_ninja,
        R.drawable.senju_hashirama,
        R.drawable.namikaze_minato_rasengan,
        R.drawable.kakashi_chidori,
    )

    val shinobiList = mutableListOf(
        Shinobi("Senju Hashirama", true),
        Shinobi("Uchiha Itachi", false),
        Shinobi("Hatake Kakashi", true),
        Shinobi("Namikaze Minato", false),
        Shinobi("Jiraya", true),
        Shinobi("Might Gai", false),
        Shinobi("Sarutobi Hiruzen", false),
        Shinobi("Nara Shikemaru", false),
        Shinobi("Uzumaki Naruto", false),
        Shinobi("Uchiha Sasuke", true),
        Shinobi("Koji Kashin", false)
    )

}
