import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinKt.initializeKoin(config: nil)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}