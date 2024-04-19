package com.jmzd.ghazal.weatherappmvvm.data.model.add_city

import com.google.gson.annotations.SerializedName

class ResponseCitiesList : ArrayList<ResponseCitiesList.ResponseCitiesListItem>(){
    data class ResponseCitiesListItem(
        @SerializedName("country")
        val country: String?, // IR
        @SerializedName("lat")
        val lat: Double?, // 35.6892523
        @SerializedName("local_names")
        val localNames: LocalNames?,
        @SerializedName("lon")
        val lon: Double?, // 51.3896004
        @SerializedName("name")
        val name: String?, // Tehran
        @SerializedName("state")
        val state: String? // Sa'dah Governorate
    ) {
        data class LocalNames(
            @SerializedName("af")
            val af: String?, // Teheran
            @SerializedName("am")
            val am: String?, // ቴህራን
            @SerializedName("an")
            val an: String?, // Teherán
            @SerializedName("ar")
            val ar: String?, // طهران
            @SerializedName("ascii")
            val ascii: String?, // Tehran
            @SerializedName("az")
            val az: String?, // Tehran
            @SerializedName("ba")
            val ba: String?, // Тегеран
            @SerializedName("be")
            val be: String?, // Тэгеран
            @SerializedName("bg")
            val bg: String?, // Техеран
            @SerializedName("bn")
            val bn: String?, // তেহরান
            @SerializedName("bo")
            val bo: String?, // ཏེ་ཧི་རན​།
            @SerializedName("br")
            val br: String?, // Tehran
            @SerializedName("bs")
            val bs: String?, // Teheran
            @SerializedName("ca")
            val ca: String?, // Teheran
            @SerializedName("cs")
            val cs: String?, // Teherán
            @SerializedName("cv")
            val cv: String?, // Тегеран
            @SerializedName("cy")
            val cy: String?, // Tehran
            @SerializedName("da")
            val da: String?, // Teheran
            @SerializedName("de")
            val de: String?, // Teheran
            @SerializedName("el")
            val el: String?, // Τεχεράνη
            @SerializedName("en")
            val en: String?, // Tehran
            @SerializedName("eo")
            val eo: String?, // Tehrano
            @SerializedName("es")
            val es: String?, // Teherán
            @SerializedName("et")
            val et: String?, // Teheran
            @SerializedName("eu")
            val eu: String?, // Teheran
            @SerializedName("fa")
            val fa: String?, // شهر تهران
            @SerializedName("feature_name")
            val featureName: String?, // Tehran
            @SerializedName("fi")
            val fi: String?, // Teheran
            @SerializedName("fo")
            val fo: String?, // Teheran
            @SerializedName("fr")
            val fr: String?, // Téhéran
            @SerializedName("fy")
            val fy: String?, // Teheran
            @SerializedName("ga")
            val ga: String?, // Teheran
            @SerializedName("gd")
            val gd: String?, // Tehran
            @SerializedName("gl")
            val gl: String?, // Teherán - تهران
            @SerializedName("he")
            val he: String?, // טהרן
            @SerializedName("hi")
            val hi: String?, // तेहरान
            @SerializedName("hr")
            val hr: String?, // Teheran
            @SerializedName("ht")
            val ht: String?, // Teheran
            @SerializedName("hu")
            val hu: String?, // Teherán
            @SerializedName("hy")
            val hy: String?, // Թեհրան
            @SerializedName("ia")
            val ia: String?, // Tehran
            @SerializedName("id")
            val id: String?, // Teheran
            @SerializedName("ie")
            val ie: String?, // Teheran
            @SerializedName("io")
            val io: String?, // Tehran
            @SerializedName("is")
            val isX: String?, // Teheran
            @SerializedName("it")
            val `it`: String?, // Teheran
            @SerializedName("ja")
            val ja: String?, // テヘラン
            @SerializedName("ka")
            val ka: String?, // თეირანი
            @SerializedName("kk")
            val kk: String?, // Тегеран
            @SerializedName("kl")
            val kl: String?, // Tehran
            @SerializedName("kn")
            val kn: String?, // ತೆಹ್ರಾನ್
            @SerializedName("ko")
            val ko: String?, // 테헤란
            @SerializedName("ku")
            val ku: String?, // Tehran
            @SerializedName("kw")
            val kw: String?, // Tehran
            @SerializedName("la")
            val la: String?, // Teheranum
            @SerializedName("lb")
            val lb: String?, // Teheran
            @SerializedName("lt")
            val lt: String?, // Teheranas
            @SerializedName("lv")
            val lv: String?, // Teherāna
            @SerializedName("mi")
            val mi: String?, // Tehran
            @SerializedName("mk")
            val mk: String?, // Техеран
            @SerializedName("ml")
            val ml: String?, // ടെഹ്റാന്
            @SerializedName("mn")
            val mn: String?, // Тегеран
            @SerializedName("mr")
            val mr: String?, // तेहरान
            @SerializedName("ms")
            val ms: String?, // Teheran
            @SerializedName("my")
            val my: String?, // တီဟီရန်မြို့
            @SerializedName("nl")
            val nl: String?, // Teheran
            @SerializedName("nn")
            val nn: String?, // Teheran
            @SerializedName("no")
            val no: String?, // Teheran
            @SerializedName("oc")
            val oc: String?, // Teheran
            @SerializedName("or")
            val or: String?, // ତେହେରାନ
            @SerializedName("os")
            val os: String?, // Тæхран
            @SerializedName("pa")
            val pa: String?, // ਤਹਿਰਾਨ
            @SerializedName("pl")
            val pl: String?, // Teheran
            @SerializedName("ps")
            val ps: String?, // تهران
            @SerializedName("pt")
            val pt: String?, // Teerã
            @SerializedName("rm")
            val rm: String?, // Teheran
            @SerializedName("ro")
            val ro: String?, // Teheran
            @SerializedName("ru")
            val ru: String?, // Тегеран
            @SerializedName("sc")
            val sc: String?, // Teheran
            @SerializedName("se")
            val se: String?, // Teheran
            @SerializedName("sk")
            val sk: String?, // Teherán
            @SerializedName("sl")
            val sl: String?, // Teheran
            @SerializedName("so")
            val so: String?, // Tahraan
            @SerializedName("sq")
            val sq: String?, // Teherani
            @SerializedName("sr")
            val sr: String?, // Техеран
            @SerializedName("sv")
            val sv: String?, // Teheran
            @SerializedName("sw")
            val sw: String?, // Tehran
            @SerializedName("ta")
            val ta: String?, // தெஹ்ரான்
            @SerializedName("te")
            val te: String?, // టెహరాన్
            @SerializedName("tg")
            val tg: String?, // Теҳрон
            @SerializedName("th")
            val th: String?, // เตหะราน
            @SerializedName("tk")
            val tk: String?, // Tähran
            @SerializedName("tl")
            val tl: String?, // Tehrān
            @SerializedName("tr")
            val tr: String?, // Tahran
            @SerializedName("tt")
            val tt: String?, // Тәһран
            @SerializedName("ug")
            val ug: String?, // تېھران
            @SerializedName("uk")
            val uk: String?, // Тегеран
            @SerializedName("ur")
            val ur: String?, // تہران
            @SerializedName("uz")
            val uz: String?, // Tehron
            @SerializedName("vi")
            val vi: String?, // Tehran
            @SerializedName("vo")
            val vo: String?, // Tehran
            @SerializedName("yi")
            val yi: String?, // טעהראן
            @SerializedName("yo")
            val yo: String?, // Tehran
            @SerializedName("zh")
            val zh: String? // 德黑兰
        )
    }
}