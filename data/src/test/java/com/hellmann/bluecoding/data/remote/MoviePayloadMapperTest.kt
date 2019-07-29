package com.hellmann.bluecoding.data.remote

import com.hellmann.bluecoding.data.MoviePayloadMock
import com.hellmann.bluecoding.data.remote.mapper.MoviePayloadMapper
import org.junit.Test

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 05/06/2019
 * 
 * (c) 2019 
 */class MoviePayloadMapperTest {
    private val moviePayload = MoviePayloadMock.moviePlayloadMock.value

    @Test
    fun `MoviePayload to Movie`() {
        val mapped = MoviePayloadMapper.map(moviePayload)

        //Assertions regarding the mapper
        assert(mapped.id > 0)
        assert(mapped.adult.not())
        assert(mapped.releaseDate.isEmpty().not())
        assert(mapped.backdropPath == null)
    }
}