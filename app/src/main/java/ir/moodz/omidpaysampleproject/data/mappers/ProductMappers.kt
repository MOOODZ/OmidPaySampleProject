package ir.moodz.omidpaysampleproject.data.mappers

import ir.moodz.omidpaysampleproject.data.local.ProductEntity
import ir.moodz.omidpaysampleproject.data.remote.ProductDto
import ir.moodz.omidpaysampleproject.domain.model.ProductListing

fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image
    )
}

fun ProductEntity.toProduct(): ProductListing {
    return ProductListing(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image
    )
}