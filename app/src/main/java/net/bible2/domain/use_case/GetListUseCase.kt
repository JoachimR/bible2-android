package net.bible2.domain.use_case

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.bible2.common.Resource
import net.bible2.domain.model.Twd
import net.bible2.domain.repository.TwdRepository
import retrofit2.HttpException

class GetListUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: TwdRepository
) {
    operator fun invoke(): Flow<Resource<List<Twd>>> = flow {
        try {
            emit(Resource.Loading())
            val twds = repository.getTwds()
            emit(Resource.Success(twds))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
