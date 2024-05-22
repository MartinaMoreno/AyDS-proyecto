package ayds.songinfo.home.view

import ayds.songinfo.home.model.entities.Song
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
class ChangeFormatMonthTest{
    private val song: Song.SpotifySong = mockk()
    private val releaseDateMonthResolver: ChangeFormatMonth = ChangeFormatMonth(song)

    @Test
    fun `on get release date should return month 1`() {
        every { song.releaseDate } returns "1992-01"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "enero, 1992")
    }

    @Test
    fun `on get release date should return month 2`() {
        every { song.releaseDate } returns "1992-02"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "febrero, 1992")
    }

    @Test
    fun `on get release date should return month 3`() {
        every { song.releaseDate } returns "1992-03"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "marzo, 1992")
    }

    @Test
    fun `on get release date should return month 4`() {
        every { song.releaseDate } returns "1992-04"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "abril, 1992")
    }

    @Test
    fun `on get release date should return month 5`() {
        every { song.releaseDate } returns "1992-05"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "mayo, 1992")
    }

    @Test
    fun `on get release date should return month 6`() {
        every { song.releaseDate } returns "1992-06"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "junio, 1992")
    }

    @Test
    fun `on get release date should return month 7`() {
        every { song.releaseDate } returns "1992-07"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "julio, 1992")
    }

    @Test
    fun `on get release date should return month 8`() {
        every { song.releaseDate } returns "1992-08"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "agosto, 1992")
    }

    @Test
    fun `on get release date should return month 9`() {
        every { song.releaseDate } returns "1992-09"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "septiembre, 1992")
    }

    @Test
    fun `on get release date should return month 10`() {
        every { song.releaseDate } returns "1992-10"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "octubre, 1992")
    }

    @Test
    fun `on get release date should return month 11`() {
        every { song.releaseDate } returns "1992-11"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "noviembre, 1992")
    }

    @Test
    fun `on get release date should return month 12`() {
        every { song.releaseDate } returns "1992-12"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, "diciembre, 1992")
    }

    @Test
    fun `on get release date should return month 0`() {
        every { song.releaseDate } returns "1992-00"

        val result = releaseDateMonthResolver.getReleaseDate()

        assertEquals(result, ", 1992")
    }
}