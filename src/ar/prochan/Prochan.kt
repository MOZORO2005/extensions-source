package eu.kanade.tachiyomi.extension.ar.prochan
import eu.kanade.tachiyomi.source.model.*
import eu.kanade.tachiyomi.source.online.HttpSource
import okhttp3.Request
import okhttp3.Response

class Prochan : HttpSource() {
    override val name = "Prochan"
    override val baseUrl = "https://prochan.net"
    override val lang = "ar"
    override val supportsLatest = false
    override val client = network.cloudflareClient
    
    override fun popularMangaRequest(page: Int) = Request.Builder().url("$baseUrl/manga/").build()
    override fun popularMangaParse(response: Response) = MangasPage(emptyList(), false)
    override fun latestUpdatesRequest(page: Int) = popularMangaRequest(page)
    override fun latestUpdatesParse(response: Response) = popularMangaParse(response)
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList) = popularMangaRequest(page)
    override fun searchMangaParse(response: Response) = popularMangaParse(response)
    override fun mangaDetailsRequest(manga: SManga) = popularMangaRequest(1)
    override fun mangaDetailsParse(response: Response) = SManga.create()
    override fun chapterListRequest(manga: SManga) = popularMangaRequest(1)
    override fun chapterListParse(response: Response) = emptyList<SChapter>()
    override fun pageListRequest(chapter: SChapter) = popularMangaRequest(1)
    override fun pageListParse(response: Response) = emptyList<Page>()
    override fun imageRequest(page: Page) = popularMangaRequest(1)
    override fun getFilterList() = FilterList()
}
