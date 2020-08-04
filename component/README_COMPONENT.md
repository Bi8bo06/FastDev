# component库
这个是基类的组件库，里面包含了Rxjava2、Retrofit2、Eventbus3、Fastjson和对core库的依赖，小伙伴可以依赖这个库来进行敏捷开发

## 1、涉及的依赖
该库已经引用了core、Rxjava2、Retrofit2、Rxlifecycle2以及Eventbus3.0，小伙伴不需要再重复引用，我会定期关注并更新版本，基本保证与最新版本一致
```
    dependencies {
        api 'io.reactivex.rxjava2:rxjava:2.2.12'
        api 'io.reactivex.rxjava2:rxandroid:2.1.1'

        api 'com.squareup.retrofit2:retrofit:2.6.1'
        api 'com.squareup.retrofit2:adapter-rxjava2:2.6.1'

        api 'com.alibaba:fastjson:1.2.60'

        api 'com.trello.rxlifecycle3:rxlifecycle-components:3.0.0'

        api 'org.greenrobot:eventbus:3.1.1'
    }
```

## 2、Application基类BaseApplication
1）BaseApplication内部已经实现了单例模式，可以用下面的方法来获取application单例
```
    BaseApplication.get();
```
如果你希望获取自定义的Application子类对象，你可以尝试使用下方的方式
```
    public class App extends BaseApplication {

        public static App getInstance(){
            return (App) get();
        }
        .....
    }
```
2）BaseApplication目前有两个抽象方法，分别是在onCreate()里回调的 **afterCreate()** 以及在退出应用时回调的 **beforeExit()** ，
你可以在顶层app包里自定义一个Application继承BaseApplication，并实现这两个方法（例如初始化某些资源以及退出时释放某些资源）

3）如果你希望退出整个应用，可以调用下方的方法，它会关闭你所有的Activity（前提是这些Activity继承自基类库），调用该方法后会回调 **beforeExit()**
```
    BaseApplication.get().exit();
```

4）如果你希望在app进入后台时保存关键数据，你可以重写下方的方法，将你需要保存的数据放在Bundle中传给父类
```
    @Override
    public Bundle getSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putString("key", value);
        return bundle;
    }
```
当你的app在后台被系统回收，并又重新回到前台时，你可以重写下方的方法，在该方法中获取你之前保存的关键数据
```
    @Override
    public void getRestoreInstanceState(Bundle bundle) {
        super.getRestoreInstanceState(bundle);
        if (bundle != null){
            value = bundle.getString("key", "");
        }
    }
```
5）你可以在通过 **getBaseLayoutConfig()** 获取到基类的配置对象，自由的对基类的状态控件进行统一配置

## 3、Activity基类
### 1）AbsActivity
a）AbsActivity是最底层的Activity，如果你不需要用到数据加载状态界面的话，可以选择继承这个Activity

b）我在这个Activity里注册了EventBus，因此只要是继承它的Activity都不需要再重复注册和解注册，只需要订阅即可

c）我将 **onCreate(@Nullable Bundle savedInstanceState)** 分为6个方法调用顺序分别如下
```
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        .....
        startCreate();
        setContentView(getAbsLayoutId());
        findViews(savedInstanceState);
        setListeners();
        initData();
        endCreate();
    }
```
- 重写 **startCreate()** 方法，可以在里面获取Intent的数据
- 实现 **getAbsLayoutId()** 方法，返回layout的资源id
- 实现 **findViews(savedInstanceState)** 方法，你可以在该方法里进行资源id获取对象的操作
- 重写 **setListeners()** 方法，你可以在该方法里对需要的对象设置各类监听器回调
- 重写 **initData()** 方法，你可以在该方法里初始化界面的业务数据
- 重写 **endCreate()** 方法，你可以在该方法里处理不属于上述方法分类的数据

d）你可以直接调用下面的方法来获取当前Activity的上下文
```
    getContext()
```
e）你可以重写下面的方法来拦截用户点击返回按钮的事件，返回true表示消耗掉，返回false表示继续传递事件
```
    onPressBack()
```
f）你可以直接通过下面的方法将订阅绑定生命周期，避免内存泄漏
```
    .compose(this.<T>bindUntilEvent(ActivityEvent.DESTROY))
```
g）如果你需要再Activity里直接添加Fragment，你可以直接调用下面封装好的方法，帮助你简单快速的使用
```
    /** 添加fragment */
    addFragment(@IdRes int containerViewId, Fragment fragment, @Nullable String tag)

    /** 替换fragment */
    replaceFragment(@IdRes int containerViewId, Fragment fragment, @Nullable String tag)

    /** 显示fragment */
    showFragment(Fragment fragment)

    /** 隐藏fragment */
    hideFragment(Fragment fragment)

    等等....（详见代码）
```

### 2）BaseActivity
a）BaseActivity继承自AbsActivity，并在内部增加了数据加载状态界面，如果你需要用到界面级别的数据加载状态UI可以选择继承这个Activity

b） **TitleBarLayout** 为界面顶部的标题栏，如果你不希望显示TitleBarLayout，可以调用下方的方法隐藏
```
    goneTitleBar()
```
如果你希望对TitleBarLayout做一些定制，你可以调用下面的方法来获取TitleBarLayout的对象
```
    getTitleBarLayout()
```
你可以重写下面的方法，在方法内实现用户点击标题栏的返回按钮的操作
```
    clickBackBtn()
```
c） **LoadingLayout** 为加载控件，如果你希望在异步获取数据时将界面显示为加载状态，可以调用下面的方法
```
    showStatusLoading()
```
如果你希望对LoadingLayout做一些轻量级的定制，你可以调用下面的方法获取LoadingLayout的对象
```
    getLoadingLayout()
```
d） **NoDataLayout** 为无数据控件，如果你在获取数据后需要展示无数据界面，可以调用下面的方法
```
    showStatusNoData()
```
如果你希望对NoDataLayout做一些轻量级的定制，你可以调用下面的方法获取NoDataLayout的对象
```
    getNoDataLayout()
```
e） **ErrorLayout** 为界面异常控件，如果你在获取数据后发现获取的数据不正确，可以调用下列方法显示
```
    showStatusError()
```
如果你希望对ErrorLayout做一些轻量级的定制，你可以调用下面的方法获取ErrorLayout的对象
```
    getErrorLayout()
```
如果你希望用户点击该界面可以重新加载数据的话，你可以重写下面的方法，并在方法里实现数据的加载
```
    clickReload()
```

### 3）BaseRefreshActivity
a）BaseRefreshActivity继承自AbsActivity，内部除了包含数据加载的状态界面外，还包括了一个下拉刷新控件，如果你的界面是需要下拉刷新数据的，可以选择继承这个Activity

b） **SwipeRefreshLayout** 为下拉刷新控件，你可以实现下面的方法，在方法里执行数据的刷新逻辑
```
    onDataRefresh()
```
如果你希望设置下拉进度的切换颜色，你可以调用下面的方法
```
    setSwipeRefreshColorScheme(@ColorRes int... colorResIds)
```
如果你希望设置下拉进度的背景颜色，你可以调用下面的方法
```
    setSwipeRefreshBackgroundColor(@ColorRes int colorResId)
```
**当你结束下拉刷新时（不论获取数据成功还是失败），切记要调用下面的方法，否则进度条不会隐藏**
```
    setSwipeRefreshFinish()
```
如果你希望在某些条件下禁用刷新控件，某些条件下启用的话，可以调用下面的方法
```
    setSwipeRefreshEnabled(boolean enabled)
```
c）加载状态界面的使用方式与BaseActivity一致

### 4）BaseSandwichActivity
a）BaseSandwichActivity继承自AbsActivity，内部分为上中下3个区域，中间由状态控件和刷新控件包裹，顶部和底部可以根据自己的需要放入layout。需要自定义顶部底部且由中部刷新时，可以选择继承这个Activity

b）重写 **getTopLayoutId()** 方法，传入顶部布局的id，即可把顶部布局加载进界面（不重写的话不会显示）

c）重写 **getBottomLayoutId()** 方法，传入底部布局的id，即可把底部布局加载进界面（不重写的话不会显示）

d）其余使用方法与BaseRefreshActivity一致

## 4、Fragment基类
### 1）LazyFragment
a）LazyFragment是最底层的Fragment，如果你不需要用到数据加载状态界面的话，可以选择继承这个Fragment。

b）这个Fragment实现了懒加载，即当这个Fragment显示的时候再加载数据。LazyFragment默认进行懒加载，如果你不希望懒加载可以重写下面的方法，取消懒加载设置
```
    @Override
    protected boolean configIsLazyLoad() {
        return false;
    }
```

c）同AbsActivity一样，我也将 **onViewCreated(View view, @Nullable Bundle savedInstanceState)** 分为6个方法，具体调用顺序和使用方法与AbsActivity一致，这里就不再赘述，顺序和方法名如下
```
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        .....
        startCreate();
        findViews(view, savedInstanceState);
        setListeners(view);
        initData(view);
        endCreate();
    }

    .....

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getAbsLayoutId(), container, false);
    }

    @LayoutRes
    protected abstract int getAbsLayoutId();
```

d）你可以重写下面的方法来拦截用户点击返回按钮的事件，返回true表示在当前的fragment里消耗掉返回事件，返回false则向上传递给父类
```
    onPressBack()
```
e）如果你需要在fragment里实现Activity里的OnResume的生命周期，你可以重写下面的方法，该方法的回调时机与Activity里的OnResume保持一致
```
    onFragmentResume()
```
f）如果你需要在fragment里实现Activity里的onPause的生命周期，你可以重写下面的方法，该方法的回调时机与Activity里的onPause保持一致
```
    onFragmentPause()
```
g）你可以直接通过下面的方法将订阅绑定生命周期，避免内存泄漏
```
    .compose(this.<T>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
```

### 2）BaseFragment
BaseFragment继承自LazyFragment，同样在内部增加了数据加载状态界面，用法与BaseActivity保持一致，这里不再赘述。

### 3）BaseRefreshFragment
BaseRefreshFragment继承自LazyFragment，和BaseRefreshActivity一样，包含了加载状态界面和下拉刷新，使用方法和BaseRefreshActivity里的一样。

### 4）BaseSandwichFragment
a）BaseSandwichFragment继承自LazyFragment，内部分为上中下3个区域，中间由状态控件和刷新控件包裹，顶部和底部可以根据自己的需要放入layout。使用方式和BaseSandwichActivity一致
