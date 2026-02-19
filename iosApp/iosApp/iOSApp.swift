import SwiftUI
import Foundation
import FirebaseCore
import FirebaseFirestore

@main
struct iOSApp: App {
    
    init() {
        FirebaseApp.configure()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
