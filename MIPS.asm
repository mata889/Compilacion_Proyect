.data
_numGlobal:      .word 0
_msg0:     .asciiz "awa"
   .text
   .globl main
_imprimir:
       sw $fp, -4($sp)
       sw $ra, -8($sp)
       lw $v0, _ret
       b _fin_imprimir
_fin_imprimir:
       move $sp, $fp
       lw $fp, -4($sp)
       lw $ra, -8($sp)
       jr $ra
main:
       move $fp, $sp
_etiq2:
_etiq3:

       li $v0,       b _etiq2
_etiq5:

       li $v0,
       li $v0, 4
       la $a0, _msg0
       syscall_etiq7:
       lw $t0, _i
       li $t1, 6
       blt $t0, $t1, _etiq8
       b _null
_etiq9:
       b _etiq7
_etiq8:
_etiq11:

       li $v0,       b _etiq9
       li $v0,10
       syscall
