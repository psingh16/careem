package com.careem.hacathon.dao.model;

import com.careem.hacathon.pojos.GoodsCategory;
import com.careem.hacathon.pojos.GoodsType;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Entity
@Table(name = "PRICE")
@Data
@NamedQueries({
        @NamedQuery(
                name = "Price.findByGoodsTypeAndGoodsCategory",
                query = "from Price m where m.goodsType = :goodsType and m.category = :goodsCategory"
        )

})
public class Price {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", nullable = false)
        private int id;

        @Column(name = "GOODS_TYPE", nullable = false)
        @Enumerated(EnumType.STRING)
        private GoodsType goodsType;

        @Column(name = "GOODS_CATEGORY", nullable = false)
        @Enumerated(EnumType.STRING)
        private GoodsCategory category;

        @Column(name = "WEIGHT", nullable = false)
        private double wight;

        @Column(name = "UP_TO_KMS", nullable = false)
        private int distance;

        @Column(name = "PRICE", nullable = false)
        private double price;

}



