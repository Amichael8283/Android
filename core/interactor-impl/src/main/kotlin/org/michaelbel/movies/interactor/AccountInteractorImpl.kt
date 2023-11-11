package org.michaelbel.movies.interactor

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.michaelbel.movies.common.dispatchers.MoviesDispatchers
import org.michaelbel.movies.interactor.usecase.DelayUseCase
import org.michaelbel.movies.persistence.database.entity.AccountDb
import org.michaelbel.movies.repository.AccountRepository

@Singleton
internal class AccountInteractorImpl @Inject constructor(
    private val dispatchers: MoviesDispatchers,
    private val accountRepository: AccountRepository,
    private val delayUseCase: DelayUseCase
): AccountInteractor {

    override val account: Flow<AccountDb?> = accountRepository.account

    override suspend fun accountId(): Int? {
        return withContext(dispatchers.io) {
            accountRepository.accountId()
        }
    }

    override suspend fun accountExpireTime(): Long? {
        return withContext(dispatchers.io) {
            accountRepository.accountExpireTime()
        }
    }

    override suspend fun accountDetails() {
        return withContext(dispatchers.io) {
            delay(delayUseCase.networkRequestDelay())
            accountRepository.accountDetails()
        }
    }
}