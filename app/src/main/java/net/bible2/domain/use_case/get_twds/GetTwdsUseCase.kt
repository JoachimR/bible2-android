package net.bible2.domain.use_case.get_twds

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.bible2.common.Resource
import net.bible2.data.remote.dto.toTwd
import net.bible2.domain.model.Twd
import net.bible2.domain.repository.TwdRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTwdsUseCase @Inject constructor(
    private val repository: TwdRepository
) {
    operator fun invoke(): Flow<Resource<List<Twd>>> = flow {
        try {
            emit(Resource.Loading<List<Twd>>())
            val twds = repository.getTwds().map { it.toTwd() }
            emit(Resource.Success<List<Twd>>(twds))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Twd>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Twd>>("Couldn't reach server. Check your internet connection."))
        }
    }
}