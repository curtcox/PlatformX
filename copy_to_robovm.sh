# Copy the files need by the iOS app from the shared project, to the
# iOS project.

rm -r RoboVM/src/main/java/
mkdir -p RoboVM/src/main/java/ios
mkdir -p RoboVM/src/main/java/x
mkdir -p RoboVM/src/main/java/hash
mkdir -p RoboVM/src/main/java/google
cp -R OC1/src/ios RoboVM/src/main/java/
cp -R OC1/src/x RoboVM/src/main/java/
cp -R OC1/src/hash RoboVM/src/main/java/
cp -R OC1/src/google RoboVM/src/main/java/

# The following line copies any changes made directly in the iOS project,
# back to the shared project.
# cp -R  RoboVM/src/main/java/ OC1/src/
