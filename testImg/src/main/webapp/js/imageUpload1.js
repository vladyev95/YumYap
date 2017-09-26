/**
 * 
 */
        $(document).ready(function () {
            'use strict';
            $('#uploadButton').on('click', uploadToS3);
        });

        function uploadToS3() {
            'use strict';
            let image = document.getElementById('imageFile').files[0];
            if (image) {

                let s3 = new AWS.S3();
                AWS.config.update({
                    // TODO: insert credentials here
                    accessKeyId: "AKIAJJ652PYUGFZSJG2A",
                    secretAccessKey: "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",
                    region: "us-east-1"
                });
                let params = {
                    Body: image,
                    Bucket: 'yumyapimg',
                    Key: image.name + '--' + new Date().getTime()
                };

                s3.putObject(params, function (err, data) {
                    console.log(err, data);
                })
            }
        };