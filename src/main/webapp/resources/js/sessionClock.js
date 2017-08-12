                    var min=15;
                    var sec=60;
                    function timeMin() {
                        if(min==0) {
                            location.href="http://localhost:8080/System/faces/index.xhtml";
                        } else {
                            min--;
                            document.getElementById('min').innerHTML=(min < 15) ? (0 + min) : min;
                            setTimeout("timeMin();", 60000);
                        }
                    }
                    function timeSec() {
                        if(sec > 54000) {
                            location.href="http://localhost:8080/System/faces/index.xhtml";
                        } else {
                            sec--;
                            document.getElementById('sec').innerHTML=(sec < 54000) ? (0 + sec) : sec;
                            setTimeout("timeSec();", 1000);
                            if (sec == 0) {
                                sec = 60;
                            }
                        }
                    }


