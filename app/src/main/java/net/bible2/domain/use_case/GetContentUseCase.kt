package net.bible2.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.bible2.common.Resource
import net.bible2.domain.model.TheWordFileContent
import net.bible2.domain.repository.TwdRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
    private val repository: TwdRepository
) {
    operator fun invoke(url: String): Flow<Resource<TheWordFileContent>> = flow {
        try {
            emit(Resource.Loading())
            repository.getTheWordFileContent(url)?.let { content ->
                emit(Resource.Success(content))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}