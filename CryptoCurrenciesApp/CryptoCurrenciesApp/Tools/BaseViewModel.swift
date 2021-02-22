
import Foundation
import RxSwift

class BaseViewModel {

    let compositeDisposable = CompositeDisposable()

    let progressSubject = PublishSubject<OperationStatus>()
    let errorSubject = PublishSubject<Error>()

    func postStartOperation() {
        progressSubject.onNext(OperationStatus.started)
    }

    func postFinishOperation() {
        progressSubject.onNext(OperationStatus.finished)
    }

    func baseErrorConsumer(_ error: Error) {
        postFinishOperation()
        errorSubject.onNext(error)
    }

    func destroy() {
        compositeDisposable.dispose()
    }

    enum OperationStatus {
        case started
        case inProgress
        case finished
    }
}