package com.klpc.stadspring.domain.cart.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartProduct is a Querydsl query type for CartProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartProduct extends EntityPathBase<CartProduct> {

    private static final long serialVersionUID = 582809688L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartProduct cartProduct = new QCartProduct("cartProduct");

    public final NumberPath<Long> adverseId = createNumber("adverseId", Long.class);

    public final QCart cart;

    public final NumberPath<Long> contentId = createNumber("contentId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.klpc.stadspring.domain.product.entity.QProduct product;

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public QCartProduct(String variable) {
        this(CartProduct.class, forVariable(variable), INITS);
    }

    public QCartProduct(Path<? extends CartProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartProduct(PathMetadata metadata, PathInits inits) {
        this(CartProduct.class, metadata, inits);
    }

    public QCartProduct(Class<? extends CartProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new QCart(forProperty("cart"), inits.get("cart")) : null;
        this.product = inits.isInitialized("product") ? new com.klpc.stadspring.domain.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

