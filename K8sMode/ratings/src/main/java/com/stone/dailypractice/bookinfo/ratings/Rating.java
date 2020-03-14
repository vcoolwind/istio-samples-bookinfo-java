package com.stone.dailypractice.bookinfo.ratings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@TypeDefs( { @TypeDef( name = "uuid-pgh2", typeClass = PostgresH2UUIDType.class, defaultForType = UUID.class ) } )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue( generator = "uuid2" )
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    private UUID id;
    private String reviewer;
    private UUID productId;
    private int stars;
}