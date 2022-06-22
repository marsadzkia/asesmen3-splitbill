package org.d3if2107.splitbill.model

import org.d3if2107.splitbill.db.SplitBillsEntity

fun SplitBillsEntity.splitBill(): JumlahTagihan {
    val jmlhOrgF = jmlhOrang
    val ppn = tagihan * 0.11
    val patungan = ((tagihan + ppn) / jmlhOrgF).toString()
    return JumlahTagihan(patungan)
}