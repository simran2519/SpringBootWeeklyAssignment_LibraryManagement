package com.library.management.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fine
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fineId;
    @Column
    private long amount;
    private long bookId;
}
