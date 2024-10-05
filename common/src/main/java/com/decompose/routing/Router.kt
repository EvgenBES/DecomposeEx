package com.decompose.routing

import com.decompose.navigation.Destination


// Router - маршрутизатор фичи

interface Router {
    fun push(destination: Destination)
    fun replace(destination: Destination)
    fun replaceAll(destinations: List<Destination>)

    fun activate(destination: Destination)

    fun back()
    fun dismiss()
}

//struct ProfileSettingsRouter: ErrorRouterTrait {
//    private let todoRoute: TODORoute
//    private let setupPasscodeRoute: SetupPasscodeRoute
//    private let backOrDismissRoute: BackOrDismissRoute
//
//    init(todoRoute: TODORoute, setupPasscodeRoute: SetupPasscodeRoute, backOrDismissRoute: BackOrDismissRoute) {
//        self.todoRoute = todoRoute
//        self.setupPasscodeRoute = setupPasscodeRoute
//        self.backOrDismissRoute = backOrDismissRoute
//    }
//
//    func showChangePhoneNumber() {
//        todoRoute.navigate()
//    }
//
//    func showChangeEmail() {
//        todoRoute.navigate()
//    }
//
//    func showSetupPasscode(completion: @escaping (Bool) -> Void) {
//        let context = SetupPasscodeContext(shouldSetupBiometry: false) {
//        backOrDismissRoute.navigate()
//        completion($0)
//    }
//
//        setupPasscodeRoute.navigate(context: context)
//    }
//}

