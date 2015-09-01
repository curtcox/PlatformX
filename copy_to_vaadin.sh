# Copy the files need by the Vaadin app from the shared project, to the
# Vaadin project.

rm -r vaadin/src/main/java/
mkdir -p vaadin/src/main/java/va
mkdir -p vaadin/src/main/java/x
mkdir -p vaadin/src/main/java/hash
mkdir -p vaadin/src/main/java/google
cp -R OC1/src/va vaadin/src/main/java/
cp -R OC1/src/x vaadin/src/main/java/
cp -R OC1/src/hash vaadin/src/main/java/
cp -R OC1/src/google vaadin/src/main/java/

# The following line copies any changes made directly in the Vaadin project,
# back to the shared project.
# cp -R  vaadin/src/main/java/ OC1/src/
