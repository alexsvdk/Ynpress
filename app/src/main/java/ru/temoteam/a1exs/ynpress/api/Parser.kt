package ru.temoteam.a1exs.ynpress.api
import ru.temoteam.a1exs.ynpress.api.objects.User
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object Parser {

    fun parseProfile(profileHTML: String?): User.Profile{
        val page = Jsoup.parse(profileHTML).getElementsByClass("inner")[1]
        val imgURL = page.getElementsByClass("pic-wrapper")[0].attr("style").substringAfter("url(").substringBefore(")")
        val info = page.getElementsByClass("info")[0]
        val name = info.getElementsByClass("name-surname").text()
        val rating = info.getElementsByClass("rating").select("span").text()
        val region = info.getElementsByClass("yp-select white").select("option").find { it.hasAttr("selected") }
        val regionName = region!!.text()
        val regionID = region.`val`()
        val inputs = page.getElementsByClass("yp-input").select("input")
        val phone = inputs.find { it.hasAttr("name")&&it.attr("name")=="tel" }!!.`val`()



        return User.Profile(
                name.substringBefore(" "),
                name.substringAfter(" "),
                "",regionName,regionID,phone,imgURL,rating
                )
    }
}