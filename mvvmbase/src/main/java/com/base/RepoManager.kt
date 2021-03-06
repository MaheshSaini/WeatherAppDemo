package com.base


/**
 * The Class Responsible
 *
 */
interface RepoManager {
    /**
     * The Generic Api Provider Which can be Accessed from Any Consumer Module and Provides the Api Interface object Reference Generated by RetroFit.
     *
     * @param T the Type of Api Interface
     * @param baseUrl Your Base URL
     * @param apiClass Class Of Type of your Api Interface
     * @return the Api Interface object
     */
    @Deprecated(
            "Replace with getRemote().getApi(...)",
            ReplaceWith(
                    expression = "getRemote()?.getApi(baseUrl, apiClass)",
                    imports = arrayOf("com.hcin.base.repo.remote.Remote")
            )
    )
    fun <T> getApi(baseUrl: String, apiClass: Class<T>): T?

    /**
     * The Generic Self Signed Api Provider Which can be Accessed from Any Consumer Module and Provides the Api Interface object Reference Generated by RetroFit.
     *
     * @param T the Type of Api Interface
     * @param baseUrl Your Base URL
     * @param apiClass Class Of Type of your Api Interface
     * @return the Api Interface object
     */
    @Deprecated(
            "Replace with getRemote()?.getSelfSignedApi(...)",
            ReplaceWith(
                    expression = "getRemote()?.getSelfSignedApi(baseUrl, apiClass)",
                    imports = arrayOf("com.hcin.base.repo.remote.Remote")
            )
    )
    fun <T> getSelfSignedApi(baseUrl: String, apiClass: Class<T>): T?

    /**
     * SecureShaderPreferences Object Responsible for Saving your key-Value Pair Data Securely.
     * Recommended While Saving Small Amount of Data
     *
     * @return the SecureShaderPreferences Object Reference
     */
    @Deprecated(
            "Replace with getLocal().getSharedPreferences()",
            ReplaceWith(
                    expression = "getLocal()?.getSharedPreferences()",
                    imports = arrayOf("com.hcin.base.repo.local.Local")
            )
    )
    fun getSharedPreferences(): SecureSharedPreference


    /**
     * The Generic Unsafe Api Provider Which can be Accessed from Any Consumer Module and Provides the Api Interface object Reference Generated by RetroFit.
     *
     * Unsafe  = This Will ignore SSL Certification Validity and Allow All from TrustManager
     *
     * @param T the Type of Api Interface
     * @param baseUrl Your Base URL
     * @param apiClass Class Of Type of your Api Interface
     * @return the Api Interface object
     */
    @Deprecated(
            "Replace with getRemote().getSelfSignedApi(...)",
            ReplaceWith(
                    expression = "getRemote()?.getUnsafeApi(baseUrl, apiClass)",
                    imports = arrayOf("com.hcin.base.repo.remote.Remote")
            )
    )
    @ExperimentalMultiplatform
    fun <T> getUnsafeApi(baseUrl: String, apiClass: Class<T>): T?

    /**
     * PersistenceStore Object Responsible for Saving your key-Value Pair Data Securely.
     * Recommended While Saving Big Amount of Data
     *
     * @return
     */
    @Deprecated(
            "Replace with getLocal().getPersistenceStore()",
            ReplaceWith(
                    expression = "getLocal()?.getPersistenceStore()",
                    imports = arrayOf("com.hcin.base.repo.local.Local")
            )
    )
    fun getPersistenceStore(): PersistenceStore

    /**
     * The App Level Things Holder
     *
     * @return Globals
     */
    fun getGlobals(): Globals

    /**
     * Returns the Remote APi's As per Requirements.
     */
    fun getRemote(): Remote?

    /**
     * Returns the Local Api's As per Requirements.
     */
    fun getLocal(): Local?


}