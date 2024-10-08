package com.blackstone.decomposetest

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.decompose.di.ComponentFactory
import com.blackstone.decomposetest.decompose.koin.KRootComponent
import com.blackstone.decomposetest.decompose.koin.KRootContent
import com.blackstone.decomposetest.decompose.koin.di.KModule
import com.blackstone.decomposetest.decompose.modules.MRootComponent
import com.blackstone.decomposetest.decompose.modules.MRootContent
import com.decompose.cards.di.CardsModule
import com.decompose.dashboard.di.DashboardModule
import com.decompose.details.di.DetailsModule
import com.decompose.splash.di.SplashModule
import com.decompose.tab1.di.Tab1Module
import com.decompose.tab2.di.Tab2Module
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                KModule().module,
                SplashModule().module,
                DashboardModule().module,
                Tab1Module().module,
                Tab2Module().module,
                CardsModule().module,
                DetailsModule().module,
            )
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = MRootComponent(
            componentFactory = ComponentFactory(getKoin()),
            componentContext = defaultComponentContext(),
        )

        setContent {
            MRootContent(root)
        }
    }
}
