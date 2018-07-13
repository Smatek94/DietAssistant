package com.skolimowskim.dietassistant.util

import io.reactivex.disposables.Disposable

object DisposableHelper {

    fun dispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed)
            disposable.dispose()
    }
}
