; Run this script with AutoHotkey, obtainable from www.autohotkey.com

#?::ListHotKeys


CapsLock::
SendInput {U+0192}
SetCapsLockState Off

;
; Ctrl. send •
; Ctrl' send ´
; Ctrl< send «
; Ctrl> send »
; Ctrl-Alt-Shift <  send ¦¦|¦¦¦|||¦
;
^.::SendInput  {U+2022}
^'::SendInput  {U+00B4}
^>::SendInput  {U+00BB}
^!+|::SendInput {U+00A6}
^<::SendInput {U+00AB}

;
; Greek AltGr+letter  abcdeklmnoprstuxw
;                     αβγδεκλμνοπρστυχω
;
<^>!a::SendInput {U+03B1}
<^>!b::SendInput {U+03B2}
<^>!c::SendInput {U+03B3}
<^>!d::SendInput {U+03B4}
<^>!e::SendInput {U+03B5}
<^>!k::SendInput {U+03BA}
<^>!l::SendInput {U+03BB}
<^>!m::SendInput {U+03BC}
<^>!n::SendInput {U+03BD}
<^>!o::SendInput {U+03BF}
<^>!p::SendInput {U+03C0}
<^>!r::SendInput {U+03C1}
<^>!s::SendInput {U+03C3}
<^>!t::SendInput {U+03C4}
<^>!u::SendInput {U+03C5}
<^>!x::SendInput {U+03C7}
<^>!w::SendInput {U+03C9}
NumPadDiv::SendInput  {U+00F7}
NumPadMult::SendInput {U+00D7}