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

###up
* non-invasive
* less template code
###down
* thick granularity of control
* can`t guarantee correct dispose (ex1: activityB on top
, activityA is below activityB .Then start a rx stream on activityA ,
we bind it at activityB , i have no idea that where one stream start from ,
then we close activityB will result in that stream close . Shame)


##other plan
* compile-time modify
* RxLifecycle (your Activity , Fragment need extends RxActivity or RxFragment)
