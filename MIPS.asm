.data
_numGlobal:      .word 0
   .text
   .globl main
main:
       move $fp, $sp
       lw $t0, _
       sw $t0, _varchar
       li $t0, 0
       sw $t0, _varchar
       li $t0, 'a'
       sb $t0, _varExeter
       lw $t0, _True
       sw $t0, _varEchelon
       lw $t1, _varchar
       add $t2, $t0, $t1
       sw $t2, _x
       li $v0,10
       syscall
