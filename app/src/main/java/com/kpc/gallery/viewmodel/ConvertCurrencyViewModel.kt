package com.kpc.gallery.viewmodel

/*import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kpc.gallery.BuildConfig
import com.kpc.gallery.mappers.toPresentation
import com.kpc.gallery.model.CurrencyInfoPresentation
import com.kpc.gallery.model.CurrencyPresentation
import com.kpc.gallery.model.CurrencyRatesInfoPresentation
import com.kpc.gallery.model.CurrencyRatesPresentation
import com.kpc.domain.model.CurrencyRates
import com.kpc.domain.model.CurrencyRatesRequest
import com.kpc.domain.usecases.CurrencyRatesBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

class ConvertCurrencyViewModel(
    private val currencyRatesUseCase: CurrencyRatesBaseUseCase
) : BaseViewModel() {

    private var searchJob: Job? = null

    private val _currencyList = MutableLiveData<CurrencyPresentation>()

    val selectedCurrency: LiveData<CurrencyInfoPresentation>
        get() = _selectedCurrency
    private val _selectedCurrency = MutableLiveData<CurrencyInfoPresentation>()

    val selectedCurrencyRates: LiveData<CurrencyRatesInfoPresentation>
        get() = _selectedCurrencyRates
    private val _selectedCurrencyRates = MutableLiveData<CurrencyRatesInfoPresentation>()

    val currencyRateList: LiveData<CurrencyRatesPresentation>
        get() = _currencyRateList
    private val _currencyRateList = MutableLiveData<CurrencyRatesPresentation>()

    val convertResult: LiveData<String>
        get() = _convertResult
    private val _convertResult = MutableLiveData<String>().apply {
        value = ""
    }

    val convertAmount = MutableLiveData<String>().apply {
        value = ""
    }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        onResultError(exception.message)
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    fun init(currencyList: CurrencyPresentation, selectedCurrencyInfo: CurrencyInfoPresentation) {
        _currencyList.value = currencyList
        _selectedCurrency.value = selectedCurrencyInfo
        searchJob = launchCoroutine {
            onResultLoading()
            currencyRatesUseCase(CurrencyRatesRequest("", selectedCurrencyInfo.code ?: "")).collect { results ->
                onResultComplete(results)
            }
        }
    }

    fun selectedCurrencyRate(selectedCurrencyRates: CurrencyRatesInfoPresentation) {
        _selectedCurrencyRates.value = selectedCurrencyRates
    }

    fun onClickConvert(view: View) {
        _selectedCurrencyRates.value?.value?.let { currencyRate ->
            if (convertAmount.value?.isNotEmpty() == true) {
                val convertAmount = convertAmount.value?.toDouble() ?: 0.0
                val total = convertAmount * currencyRate
                _convertResult.value = "$total ${_selectedCurrencyRates.value?.code}"
            }
        }
    }

    fun onClickClear(view: View) {
        _convertResult.value = ""
        convertAmount.value = ""
    }

    private fun onResultComplete(currencyRate: CurrencyRates) {
        if(currencyRate.valid) {
            _currencyList.value?.currencies?.let { currencyMap ->
                currencyRate.ratesInfo.map { it.name = currencyMap[it.code] }
            }
            _currencyRateList.value = currencyRate.toPresentation()
            _selectedCurrencyRates.value = currencyRate.ratesInfo[0].toPresentation()
            dismissLoading()
        } else {
            val errorMessage = "${currencyRate.error?.code} : ${currencyRate.error?.message}"
            onResultError(errorMessage)
        }

    }

    private fun onResultLoading() {
        //EspressoIdlingResource.increment()
        showLoading()
    }

    private fun onResultError(message: String?) {
        //EspressoIdlingResource.increment()
        error.postValue(message)
        dismissLoading()
    }
}*/



