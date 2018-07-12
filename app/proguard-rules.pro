# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#---------------------------------基本指令区----------------------------------
# 设置混淆的压缩比率 0 ~ 7
-optimizationpasses 5
# 混淆后类名都为小写   Aa aA
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
#不做预校验的操作
-dontpreverify
# 混淆时不记录日志
-verbose
# 混淆采用的算法.
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保留代码行号，方便异常信息的追踪
-keepattributes SourceFile,LineNumberTable

#dump文件列出apk包内所有class的内部结构
-dump class_files.txt
#seeds.txt文件列出未混淆的类和成员
-printseeds seeds.txt
#usage.txt文件列出从apk中删除的代码
-printusage unused.txt
#mapping文件列出混淆前后的映射
-printmapping mapping.txt

-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}



#---------------------依赖注入---------------------
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
#---------------------依赖注入----------------------



# ---------------------------------------OkHttp3---------------------------------------------------
-dontwarn okhttp3.**
-dontwarn okio.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# ---------------------------------------OkHttp3---------------------------------------------------



# ----------------------------------RxJava RxAndroid-----------------------------------------------
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
# ----------------------------------RxJava RxAndroid-----------------------------------------------



#-------------------------------------okhttputils--------------------------------------------------
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
#-------------------------------------okhttputils--------------------------------------------------



#----------------------------------Begin: Gson  ---------------------------------------------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.dawoo.lotterybox.bean.**{*;}   #------实体类不可混淆
-keep class com.dawoo.lotterybox.net.HttpResult.**{*;}   #------模板实体类不可混淆
# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##-----------------------------------End: Gson ------------------------------------- ----------




#---------------------------------------Rxbus--------------------------------------------------
-keep class com.hwangjr.rxbus.** { *; }
#---------------------------------------Rxbus------------------------------------------------




#---------------------------------webview------------------------------------
-keepclassmembers class com.dawoo.lotterybox.view.activity.webview.WebViewActivity {
   public *;
}
-keepclassmembers class com.dawoo.lotterybox.view.activity.webview.WebViewActivity.InJavaScriptGame {
   public *;
}
-keepclassmembers class com.dawoo.lotterybox.view.activity.webview.WebViewActivity.InJavaScriptCommon {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
     public void *(android.webkit.WebView, jav.lang.String);
}

#---------------------------------webview------------------------------------






#########################################################
#         retrofit                                      #
#                                                       #
-dontnote retrofit2.Platform                            #
-dontnote retrofit2.Platform$IOS$MainThreadExecutor     #
-dontwarn retrofit2.Platform$Java8                      #
-keepattributes Signature                               #
-keepattributes Exceptions                              #
-dontwarn okio.**                                       #
-dontwarn javax.annotation.**                           #
#                                                       #
#########################################################
-keep class com.dawoo.lotterybox.net.** { *; }     #对retrofit框架的封装，不可混淆。




############################################################################################################
#                                           glide                                                          #
#                                                                                                          #
-keep public class * implements com.bumptech.glide.module.GlideModule                                      #
-keep public class * extends com.bumptech.glide.module.AppGlideModule                                      #
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {                           #
  **[] $VALUES;                                                                                            #
  public *;                                                                                                #
}                                                                                                          #
#                                                                                                          #
# for DexGuard only                                                                                        #
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule                                  #
#-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule                                       #
#                                                                                                          #
############################################################################################################

###################################################
#########AVLoadingIndicatorView####################
###################################################
-keep class com.wang.avi.** { *; }#################
-keep class com.wang.avi.indicators.** { *; }######
###################################################

###############################################################################################
############       BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}
##############################################################################################

###############################################################################################
##########################com.youth.banner:banner##############################################

# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
###############################################################################################


#######################################################
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
   @butterknife.* <methods>;
}

##########################################################
#         org.apache.httpcomponents:httpcore:4.4.4
-keep class android.net.http.** { *; }
-keep class org.apache.http.** { *; }
-dontwarn android.net.http.**
-dontwarn org.apache.http.**


-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**
