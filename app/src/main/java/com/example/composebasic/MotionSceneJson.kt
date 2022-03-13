package com.example.composebasic

val demo="""
    {
    ConstraintSets: {
        start: {
            loginup: {
                top: ['parent','top'],
                bottom: ['parent','bottom'],
                end: ['parent','end'],
                width: 'preferWrap',
                height: 'parent',
                custom: {
                    background: '#3AA9AB'
                }
            },
            guideline: {
                type: 'hGuideline',
                percent: 0.5
            },
            sub1up: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['guideline','top',2],
                alpha: 1
            },
            sub1in: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['guideline','top',2],
                alpha: 0
            },
            sub2up: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                top: ['guideline','bottom',2],
                alpha: 1
            },
            sub2in: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                top: ['guideline','bottom',2],
                alpha: 0
            },
            titleup: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['sub1up','top',30],
                alpha: 1
            },
            titlein: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['sub1in','top',30],
                alpha: 0
            },
            btnview: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                top: ['sub2up','bottom',30]
            },
            btnstarttitle: {
                start: ['btnview','start'],
                end: ['btnview','end'],
                top: ['btnview','top'],
                bottom: ['btnview','bottom'],
                alpha: 1
            },
            btnendtitle: {
                start: ['btnview','start'],
                end: ['btnview','end'],
                top: ['btnview','top'],
                bottom: ['btnview','bottom'],
                alpha: 0
            },
            accountemail: {
                top: ['parent','top'],
                bottom: ['parent','bottom'],
                start: ['parent','start'],
                end: ['loginup','start']
            },
            wx: {
                bottom: ['loginintip','top',20],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            qq: {
                bottom: ['wx','bottom'],
                top: ['wx','top'],
                end: ['wx','start',10]
            },
            email: {
                bottom: ['wx','bottom'],
                top: ['wx','top'],
                start: ['wx','end',10]
            },
            loginintip: {
                bottom: ['accountemail','top',10],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 1
            },
            loginuptip: {
                bottom: ['accountemail','top',10],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 0
            },
            password: {
                top: ['accountemail','bottom',10],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            forget: {
                top: ['password','bottom',10],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            btnlogin: {
                top: ['forget','bottom',10],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            btnup: {
                top: ['btnlogin','top'],
                bottom: ['btnlogin','bottom'],
                start: ['btnlogin','start'],
                end: ['btnlogin','end'],
                alpha: 0
            },
            btnin: {
                top: ['btnlogin','top'],
                bottom: ['btnlogin','bottom'],
                start: ['btnlogin','start'],
                end: ['btnlogin','end'],
                alpha: 1
            },
            logintitlein: {
                bottom: ['email','top',20],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 1
            },
            logintitleup: {
                bottom: ['email','top',20],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 0
            }
        },
        end: {
            loginup: {
                top: ['parent','top'],
                bottom: ['parent','bottom'],
                start: ['parent','start'],
                width: 'preferWrap',
                height: 'parent',
                custom: {
                    background: '#38B791'
                }
            },
            guideline: {
                type: 'hGuideline',
                percent: 0.5
            },
            sub1up: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['guideline','top',2],
                alpha: 0
            },
            sub1in: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['guideline','top',2],
                alpha: 1
            },
            sub2up: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                top: ['guideline','bottom',2],
                alpha: 0
            },
            sub2in: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                top: ['guideline','bottom',2],
                alpha: 1
            },
            titleup: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['sub1up','top',30],
                alpha: 0
            },
            titlein: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                bottom: ['sub1in','top',30],
                alpha: 1
            },
            btnview: {
                start: ['loginup','start'],
                end: ['loginup','end'],
                top: ['sub2up','bottom',30]
            },
            btnstarttitle: {
                start: ['btnview','start'],
                end: ['btnview','end'],
                top: ['btnview','top'],
                bottom: ['btnview','bottom'],
                alpha: 0
            },
            btnendtitle: {
                start: ['btnview','start'],
                end: ['btnview','end'],
                top: ['btnview','top'],
                bottom: ['btnview','bottom'],
                alpha: 1
            },
            accountemail: {
                top: ['parent','top'],
                bottom: ['parent','bottom'],
                start: ['loginup','end'],
                end: ['parent','end']
            },
            wx: {
                bottom: ['loginintip','top',20],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            qq: {
                bottom: ['wx','bottom'],
                top: ['wx','top'],
                end: ['wx','start',10]
            },
            email: {
                bottom: ['wx','bottom'],
                top: ['wx','top'],
                start: ['wx','end',10]
            },
            loginintip: {
                bottom: ['accountemail','top',10],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 0
            },
            loginuptip: {
                bottom: ['accountemail','top',10],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 1
            },
            password: {
                top: ['accountemail','bottom',10],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            forget: {
                top: ['password','bottom',10],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            btnlogin: {
                top: ['forget','bottom',10],
                start: ['accountemail','start'],
                end: ['accountemail','end']
            },
            btnup: {
                top: ['btnlogin','top'],
                bottom: ['btnlogin','bottom'],
                start: ['btnlogin','start'],
                end: ['btnlogin','end'],
                alpha: 1
            },
            btnin: {
                top: ['btnlogin','top'],
                bottom: ['btnlogin','bottom'],
                start: ['btnlogin','start'],
                end: ['btnlogin','end'],
                alpha: 0
            },
            logintitlein: {
                bottom: ['email','top',20],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 0
            },
            logintitleup: {
                bottom: ['email','top',20],
                start: ['accountemail','start'],
                end: ['accountemail','end'],
                alpha: 1
            }
        }
    },
    Transition: {
        default: {
            from: 'start',
            to: 'end',
            KeyFrames: {
                KeyAttributes: [
                    {
                        target: ['loginup'],
                        frames: [0,10,90,100],
                        scaleX: [1,1.2,1.2,1]
                    },
                    {
                        target: ['btnview'],
                        frames: [10,90],
                        scaleX: 1.5
                    },
                    {
                        target: ['btnstarttitle'],
                        frames: [0,10,30,40,45,50,100],
                        alpha: [1,0.8,0.7,0.6,0.1,0,0]
                    },
                    {
                        target: ['btnstarttitle'],
                        frames: [0,10,30,40,45,50,100],
                        translationX: [5,40,50,70,90,90,90]
                    },
                    {
                        target: ['btnendtitle'],
                        frames: [0,45,50,60,70,80,90,100],
                        alpha: [0,0.2,0.3,0.4,0.6,0.8,1,1]
                    },
                    {
                        target: ['btnendtitle'],
                        frames: [0,10,30,40,45,50,100],
                        translationX: [-100,-80,-60,-40,-20,-10,0,0]
                    },
                    {
                        target: ['titleup','sub1up','sub2up'],
                        frames: [0,10,30,40],
                        translationX: [0,80,100,130]
                    },
                    {
                        target: ['titleup','sub1up','sub2up'],
                        frames: [0,30,40,100],
                        alpha: [1,1,0,0]
                    },
                    {
                        target: ['titlein','sub1in','sub2in'],
                        frames: [0,40,50,60,70,80,90,100],
                        translationX: [-280,-240,-200,-160,-120,-80,0,0]
                    },
                    {
                        target: ['titlein','sub1in','sub2in'],
                        frames: [0,30,43,100],
                        alpha: [0,0,1,1]
                    },
                    {
                        target: ['accountemail','password','forget','wx','qq','email'],
                        frames: [0,10,12,78,80,90,100],
                        alpha: [1,0.5,0,0,0.2,0.5,1]
                    },
                    {
                        target: ['btnup','loginuptip','logintitleup'],
                        frames: [0,70,80,95,100],
                        alpha: [0,0,0.2,1,1]
                    },
                    {
                        target: ['btnin','loginintip','logintitlein'],
                        frames: [0,9,10,15,78,100],
                        alpha: [1,0.2,0.5,0,0,0]
                    }
                ]
            }
        }
""".trimIndent()

// KeyPositions: [
//                ],