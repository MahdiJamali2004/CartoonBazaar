package com.solo.cartoonbazaar.domain.model

import com.solo.cartoonbazaar.R
import com.solo.cartoonbazaar.data.constantCartoonUids.AsrHajarUid
import com.solo.cartoonbazaar.data.constantCartoonUids.BenTenUid
import com.solo.cartoonbazaar.data.constantCartoonUids.BossBabyUid
import com.solo.cartoonbazaar.data.constantCartoonUids.CatDogUid
import com.solo.cartoonbazaar.data.constantCartoonUids.DokhtarKafshDoozakiUid
import com.solo.cartoonbazaar.data.constantCartoonUids.DragonRidersUid
import com.solo.cartoonbazaar.data.constantCartoonUids.KongfuPandaUid
import com.solo.cartoonbazaar.data.constantCartoonUids.LuckyLookUid
import com.solo.cartoonbazaar.data.constantCartoonUids.MrBeenUid
import com.solo.cartoonbazaar.data.constantCartoonUids.PalangDomDerazUid
import com.solo.cartoonbazaar.data.constantCartoonUids.PinkPantherUid
import com.solo.cartoonbazaar.data.constantCartoonUids.SpongeBobUid
import com.solo.cartoonbazaar.data.constantCartoonUids.TomJerryUid
import com.solo.cartoonbazaar.data.constantCartoonUids.NinjaTurtlesUid
import com.solo.cartoonbazaar.data.constantCartoonUids.OscarUid
import kotlinx.serialization.Serializable


enum class CartoonName(val value: Int) {
    SpongeBob(1),
    BenTen(2),
    DragonRiders(3),
    BossBaby(4),
    CatDog(5),
    KongFuPanda(6),
    LuckyLook(7),
    MrBeen(8),
    NinjaTurtles(9),
    PalangDomDeraz(10),
    PinkPanther(11),
    TomJerry(12),
    AsrHajar(13),
    DokhtarKafshdoozaki(14),
    Oscar(15);

    companion object {
        fun fromValue (value :Int?) : CartoonName?{
            return entries.firstOrNull { it.value == value }
        }
    }


}

fun CartoonName.toCartoonListUid(): List<String> {
    return when (this.value) {
        CartoonName.SpongeBob.value -> SpongeBobUid.listAllUid
        CartoonName.BenTen.value -> BenTenUid.listAllUid
        CartoonName.DragonRiders.value -> DragonRidersUid.listAllUid
        CartoonName.BossBaby.value -> BossBabyUid.listAllUid
        CartoonName.CatDog.value -> CatDogUid.listAllUid
        CartoonName.KongFuPanda.value -> KongfuPandaUid.listAllUid
        CartoonName.LuckyLook.value -> LuckyLookUid.listAllUid
        CartoonName.MrBeen.value -> MrBeenUid.listAllUid
        CartoonName.NinjaTurtles.value -> NinjaTurtlesUid.listAllUid
        CartoonName.PalangDomDeraz.value -> PalangDomDerazUid.listAllUid
        CartoonName.PinkPanther.value -> PinkPantherUid.listAllUid
        CartoonName.TomJerry.value -> TomJerryUid.listAllUid
        else -> emptyList()
    }
}

fun Int.toCartoonListUid(): List<String> {
    return when (this) {
        1 -> SpongeBobUid.listAllUid
        2 -> BenTenUid.listAllUid
        3 -> DragonRidersUid.listAllUid
        4 -> BossBabyUid.listAllUid
        5 -> CatDogUid.listAllUid
        6 -> KongfuPandaUid.listAllUid
        7 -> LuckyLookUid.listAllUid
        8 -> MrBeenUid.listAllUid
        9 -> NinjaTurtlesUid.listAllUid
        10 -> PalangDomDerazUid.listAllUid
        11 -> PinkPantherUid.listAllUid
        12 -> TomJerryUid.listAllUid
        13 -> AsrHajarUid.listAllUid
        14 -> DokhtarKafshDoozakiUid.listAllUid
        15 -> OscarUid.listAllUid
        else -> emptyList()
    }
}

fun Int.toPersianName(): String {
    return when (this) {
        1 -> "باب اسفنجی"
        2 -> "بن تن"
        3 -> "اژدها سواران"
        4 -> "بچه ریئس"
        5 -> "گربه سگ"
        6 -> "پاندای کنگفوکار"
        7 -> "لوک خوش شانس"
        8 -> "مستر بین"
        9 -> "لاک پشت های نینحا"
        10 -> "پلنگ دم دراز"
        11 -> "پلنگ صورتی"
        12 -> "تام و جری"
        13 -> "عصر حجر"
        14 -> "دختر کفشدوزکی"
        15 -> "اسکار"
        else -> {
            ""
        }
    }
}
fun Int.toCartoonDrawableResId(): Int {
    return when (this) {
        1 -> R.drawable.spongbob
        2 -> R.drawable.bentenjpg
        3 -> R.drawable.how_to_train_dargon
        4 -> R.drawable.boss_baby
        5 -> R.drawable.catdog
        6 -> R.drawable.kungfupanda
        7 -> R.drawable.lucky_luck2jpg
        8 -> R.drawable.mrbeen
        9 -> R.drawable.ninja_turtles1jpg
        10 -> R.drawable.marsupilami
        11 -> R.drawable.pink_panther
        12 -> R.drawable.tomjerryjpg
        13 -> R.drawable.asr_hajar
        14 -> R.drawable.dokhtar_kafshdoozaki
        15 -> R.drawable.oscar
        else -> -1
    }
}
