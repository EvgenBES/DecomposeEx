package com.decompose.routing

import com.decompose.navigation.Destination

// Route - как на него снавигироваться (включает в себя rootRouter)

interface Route {
    fun push(destination: Destination)
    fun replace(destination: Destination)
    fun replaceAll(destinations: List<Destination>)
    fun back()
    fun dismiss()
}

//struct ModuleRoute {
//    private let container: DIContainer
//    private let router: Router
//
//    init(container: DIContainer, router: Router) {
//        self.container = container
//        self.router = router
//    }
//
//    func navigate(with context: Account) {
//        let step = StepAssembly(finder: ClassFinder(), factory: RequestCustomExchangeFactory(container: container))
//        .using(UINavigationController.push())
//        .from(DefaultUINavigationControllerStep(finder: ClassFinder<UINavigationController, Account>()))
//        .using(GeneralAction.presentModally())
//        .from(GeneralStep.current())
//        .assemble()
//
//        router.commitNavigation(to: step, with: context)
//    }
//}

