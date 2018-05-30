package ru.temoteam.a1exs.ynpress.api
import org.jetbrains.anko.db.TEXT
import ru.temoteam.a1exs.ynpress.api.objects.User
import org.jsoup.Jsoup
import ru.temoteam.a1exs.ynpress.api.objects.Article
import ru.temoteam.a1exs.ynpress.api.objects.Article.*

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
        val birthday = inputs.find { it.hasAttr("name")&&it.attr("name")=="birthday" }!!.`val`()  + "."+
                inputs.find { it.hasAttr("name")&&it.attr("name")=="birthmonth" }!!.`val`()  + "."+
                info.getElementsByClass("yp-select white four-digits left").select("option").find { it.hasAttr("selected") }!!.`val`()

        return User.Profile(
                name.substringBefore(" "),
                name.substringAfter(" "),
                birthday,regionName,regionID,phone,imgURL,rating
                )
    }

    fun parseRead(readHtml:String?):List<Article>{
        val articles = mutableListOf<Article>()
        Jsoup.parse(readHtml).getElementsByClass("half-news-item").forEach {
            articles.add(Article(it.select("a")[1].text(),
                    it.select("a")[0].attr("href"),
                    it.select("a")[0].attr("data-src"),
                    it.select("span")[1].text(),
                    it.select("div > div > div")[0].text().replace("\"","")
            ))
        }
        return articles
    }

    fun parseArticle(articleHTML:String?):Content{
        val page = Jsoup.parse(articleHTML).getElementsByClass("column-three-quarters p-n")[0].getElementsByClass("inner")[0]
        val authordate = page.getElementsByClass("article-date yp-date")[0].text()

        val content = Content(authordate.substringBefore(" |"),
                authordate.substringAfter("| "),
                page.getElementsByClass("article-pic")[0].attr("style").substringAfter("url(").substringBefore(")"),
                page.getElementsByClass("yp-comments-title")[0].text().substringAfter(": ").substringBefore("\t"))

        page.getElementsByClass("article-pic")[0].remove()

        page.children().forEach {
            when{
                (it.`is`("p")) ->
                    if (it.text().length>1) content.articleParts.add(TextArticlePart(it.text(),it.select("strong").size!=0))
                (it.className() == "yp-video") ->
                        content.articleParts.add(VideoArticlePart(it.attr("data-video")))
                (it.className() == "yp-photo-gallery") ->
                        content.articleParts.add(GalleryArticlePart(it.getElementsByClass("gallery-icon landscape").select("a").eachAttr("href")))
                else -> {}

            }


        }

        return content
    }


}