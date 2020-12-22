.data
   .text
   .globl main
_awa:
       sw $fp, -4($sp)
       sw $ra, -8($sp)
       sw $s0, -12($sp)
       sw $s1, -16($sp)
       lw $v0, _par1
       b _fin_awa
_fin_awa:
       move $sp, $fp
       lw $fp, -4($sp)
       lw $ra, -8($sp)
       lw $s0, -16($sp)
       lw $s1, -20($sp)
       jr $ra
main:
       move $fp, $sp
       lw $a0, _parametro1
       lw $a1, _parametro2
       lw $a2, _parametro3
       lw $a3, _parametro4
       lw $t0, _parametro5
       sw $t0, -4($sp)
       lw $t0, _parametro6
       sw $t0, -8($sp)
       jal _awa
       li $v0,10
       syscall
