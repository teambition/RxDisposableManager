# RxDisposableManager

make this project to seek a better way to dispose rx stream(base on RxJava2)

##First try
use
<code>
RxJavaPlugins.setOnObservableSubscribe(BiFunction<Observable, Observer, Observer> onObservableSubscribe)
</code>
<br/>
Hook this and we can get all **Observer** here,
and all **Observer** can be a **Disposable**.
