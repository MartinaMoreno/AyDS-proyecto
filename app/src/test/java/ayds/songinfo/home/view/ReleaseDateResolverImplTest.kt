package ayds.songinfo.home.view

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
class ReleaseDateResolverImplTest{
    private val releaseDateResolverFactory: ReleaseDateResolverFactory =
        ReleaseDateResolverImpl()

    @Test
    fun `on day precision should return day precision resolver`() {
        val releaseDateResolverFactory: ReleaseDateResolverFactory =
            ReleaseDateResolverImpl()

        val result = releaseDateResolverFactory.getReleaseDateResolver(mockk {
            every { releaseDatePrecision } returns "day"
        })


        assertEquals(result::class.java, ChangeFormatDay::class.java)
    }

    @Test
    fun `on month precision should return month precision resolver`() {
        val result = releaseDateResolverFactory.getReleaseDateResolver(mockk {
            every { releaseDatePrecision } returns "month"
        })

        assertEquals(result::class.java, ChangeFormatMonth::class.java)
    }

    @Test
    fun `on year precision should return year precision resolver`() {
        val result = releaseDateResolverFactory.getReleaseDateResolver(mockk {
            every { releaseDatePrecision } returns "year"
        })

        assertEquals(result::class.java, ChangeFormatYear::class.java)
    }
}