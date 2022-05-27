package net.bible2.domain.use_case.get_twd

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.bible2.common.Resource
import net.bible2.domain.model.TheWordFileContent
import net.bible2.domain.repository.TwdRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTwdUseCase @Inject constructor(
    private val repository: TwdRepository
) {
    operator fun invoke(twdId: String): Flow<Resource<TheWordFileContent>> = flow {
        try {
            emit(Resource.Loading<TheWordFileContent>())
//            val twd = repository.(twdId).toTwdDetail()
//            emit(Resource.Success<TheWordFileContent>(twd))
        } catch (e: HttpException) {
            emit(
                Resource.Error<TheWordFileContent>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<TheWordFileContent>("Couldn't reach server. Check your internet connection."))
        }
    }
}