//
// Created by Алексей Рамашка on 22.02.21.
//

import Foundation

extension Double {
    func roundedToPlaces(toPlaces places:Int = 2) -> Double {
        let divisor = pow(10.0, Double(places))
        return (self * divisor).rounded() / divisor
    }
}