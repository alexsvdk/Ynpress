package ru.temoteam.a1exs.ynpress

import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith
import ru.temoteam.a1exs.ynpress.api.Parser
import ru.temoteam.a1exs.ynpress.api.Requester

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        Log.i("test",Parser.parseArticle(Requester.basereq("http://ynpress.com/%D1%8E%D0%BD%D0%BB%D0%B8%D1%87%D0%BD%D0%BE%D1%81%D1%82%D1%8C-%D0%B0%D1%80%D1%85%D0%B8%D0%BF%D0%BF-%D0%BB%D0%B5%D0%B1%D0%B5%D0%B4%D0%B5%D0%B2-%D1%8F-%D0%BF%D1%80%D0%BE%D1%81%D1%82%D0%BE/")!!.body()!!.string()).toString())

    }
}
