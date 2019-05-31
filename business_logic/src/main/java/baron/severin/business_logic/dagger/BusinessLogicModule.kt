package baron.severin.business_logic.dagger

import baron.severin.business_logic.*
import baron.severin.common.dagger.AppScope
import baron.severin.io.SubredditRepo
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
object BusinessLogicModule {

    @Provides @AppScope
    fun providesEventRelay(): EventRelay = EventRelay(PublishRelay.create())

    @Provides @AppScope
    internal fun providesEventObservable(eventRelay: EventRelay): EventObservable =
            EventObservable(eventRelay.get.hide())

    @Provides @AppScope
    internal fun providesActionRelay(): ActionRelay = ActionRelay(PublishRelay.create())

    @Provides @AppScope
    internal fun providesActionObservable(relay: ActionRelay): ActionObservable =
            ActionObservable(relay.get.hide())

    @Provides @AppScope
    internal fun providesStateRelay(): StateRelay = StateRelay(BehaviorRelay.create())

    @Provides @AppScope
    internal fun providesStateObservable(relay: StateRelay): StateObservable =
            StateObservable(
                    relay.get.hide()
                            .observeOn(AndroidSchedulers.mainThread())
            )

    @Provides @AppScope
    internal fun providesDispatcher(
            eventObservable: EventObservable,
            actionRelay: ActionRelay,
            subredditRepo: SubredditRepo
    ): Dispatcher =
            Dispatcher(
                    eventObservable,
                    actionRelay,
                    subredditRepo
            )

    @Provides @AppScope
    internal fun providesReducer(): Reducer = Reducer()

    @Provides @AppScope
    internal fun providesStore(
            reducer: Reducer,
            actionObservable: ActionObservable,
            stateRelay: StateRelay
    ): Store =
            Store(
                    initalState,
                    reducer,
                    actionObservable,
                    stateRelay
            )
}