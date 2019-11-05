package com.woocommerce.android.di

import com.woocommerce.android.support.HelpActivity
import com.woocommerce.android.support.HelpModule
import com.woocommerce.android.ui.dashboard.DashboardModule
import com.woocommerce.android.ui.login.LoginActivity
import com.woocommerce.android.ui.login.MagicLinkInterceptActivity
import com.woocommerce.android.ui.main.MainActivity
import com.woocommerce.android.ui.main.MainModule
import com.woocommerce.android.ui.mystore.MyStoreModule
import com.woocommerce.android.ui.orders.AddOrderShipmentTrackingModule
import com.woocommerce.android.ui.orders.AddOrderTrackingProviderListModule
import com.woocommerce.android.ui.orders.OrderDetailModule
import com.woocommerce.android.ui.orders.OrderFulfillmentModule
import com.woocommerce.android.ui.orders.OrderListModule
import com.woocommerce.android.ui.orders.OrderProductListModule
import com.woocommerce.android.ui.orders.notes.AddOrderNoteModule
import com.woocommerce.android.ui.prefs.AppSettingsActivity
import com.woocommerce.android.ui.prefs.AppSettingsModule
import com.woocommerce.android.ui.prefs.MainSettingsModule
import com.woocommerce.android.ui.prefs.PrivacySettingsModule
import com.woocommerce.android.ui.products.ProductDetailModule
import com.woocommerce.android.ui.products.ProductImagesModule
import com.woocommerce.android.ui.products.ProductListModule
import com.woocommerce.android.ui.refunds.RefundsModule
import com.woocommerce.android.ui.reviews.ReviewDetailModule
import com.woocommerce.android.ui.reviews.ReviewListModule
import com.woocommerce.android.ui.sitepicker.SitePickerActivity
import com.woocommerce.android.ui.sitepicker.SitePickerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.wordpress.android.login.di.LoginFragmentModule

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            MainModule::class,
            DashboardModule::class,
            MyStoreModule::class,
            OrderListModule::class,
            OrderDetailModule::class,
            OrderProductListModule::class,
            OrderFulfillmentModule::class,
            RefundsModule::class,
            AddOrderNoteModule::class,
            ProductDetailModule::class,
            ProductImagesModule::class,
            ProductListModule::class,
            ReviewListModule::class,
            ReviewDetailModule::class,
            SitePickerModule::class,
            ViewModelModule::class,
            ThreadModule::class,
            AddOrderShipmentTrackingModule::class,
            AddOrderTrackingProviderListModule::class))
    abstract fun provideMainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LoginFragmentModule::class))
    abstract fun provideLoginActivityInjector(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun provideMagicLinkInterceptActivityInjector(): MagicLinkInterceptActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            AppSettingsModule::class,
            MainSettingsModule::class,
            PrivacySettingsModule::class))
    abstract fun provideAppSettingsActivityInjector(): AppSettingsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HelpModule::class))
    abstract fun provideHelpActivity(): HelpActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(SitePickerModule::class))
    abstract fun provideSitePickerActivityInjector(): SitePickerActivity
}
