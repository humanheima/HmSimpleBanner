```
FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:mergeDebugResources'.
> 1 exception was raised by workers:
  com.android.builder.internal.aapt.v2.Aapt2Exception: Android resource compilation failed
  /Users/dumingwei/.gradle/caches/transforms-2/files-2.1/653650fe2af82f0a7b98ec0e694f63b6/res/values/values.xml:131:5-108: AAPT: error: duplicate value for resource 'attr/image_scale_type' with config ''.
      
  /Users/dumingwei/.gradle/caches/transforms-2/files-2.1/653650fe2af82f0a7b98ec0e694f63b6/res/values/values.xml:131:5-108: AAPT: error: resource previously defined here.
      
  /Users/dumingwei/AndroidStudioProjects/HmSimpleBanner/app/build/intermediates/incremental/mergeDebugResources/merged.dir/values/values.xml: error: file failed to compile.


* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 1s
14 actionable tasks: 8 executed, 6 up-to-date

```
```
<!--轮播图缩放的方式-->
<attr name="image_scale_type" format="enum">
    <enum name="fit_xy" value="0" />
    <enum name="center_crop" value="1" />
</attr>

```
[Duplicate value for resource 'attr/font' with config "](https://stackoverflow.com/questions/47668526/duplicate-value-for-resource-attr-font-with-config)