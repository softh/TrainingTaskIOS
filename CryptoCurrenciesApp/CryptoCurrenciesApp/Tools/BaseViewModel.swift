
import Foundation
import RxSwift

class BaseViewModel {

    let disposeBag = DisposeBag()

    let progressSubject = PublishSubject<OperationStatus>()
    let errorSubject = PublishSubject<Error>()

    func postStartOperation() {
        progressSubject.onNext(OperationStatus.started)
    }

    func postFinishOperation() {
        progressSubject.onNext(OperationStatus.finished)
    }
    
    func postOperationError(_ error: Error) {
        errorSubject.onNext(error)
    }

    func baseErrorConsumer(_ error: Error) {
        postFinishOperation()
        errorSubject.onNext(error)
    }

    enum OperationStatus {
        case started
        case inProgress
        case finished
    }
}
