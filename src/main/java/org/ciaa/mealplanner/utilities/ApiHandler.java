package org.ciaa.mealplanner.utilities;

import com.spoonacular.RecipesApi;
import com.spoonacular.client.*;
import com.spoonacular.client.auth.ApiKeyAuth;
import com.spoonacular.client.model.GetRandomRecipes200Response;
import com.spoonacular.client.model.GetRandomRecipes200ResponseRecipesInner;
import com.spoonacular.client.model.GetRecipeInformation200ResponseExtendedIngredientsInner;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.ciaa.mealplanner.CiaaApplication;
import org.ciaa.mealplanner.User;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class responsible for handling API requests to the Spoonacular API.
 * It uses the Spoonacular API client to make these requests.
 * <p>
 * The static initializer block of this class transforms some classes in the Spoonacular API client and tweaks
 * the expected fields of the response classes, before the ApiClient is initialized and set.
 */
public class ApiHandler
{
    /**
     * The Spoonacular API client used to make API calls.
     */
    private static final ApiClient CLIENT;
    /**
     * A boolean flag to check if the API client has been authenticated.
     */
    private static boolean authenticated = false;

    static {
        try {
            transformClasses();
            tweakResponses();
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        CLIENT = Configuration.getDefaultApiClient();
    }

    /**
     * @hidden
     */
    private ApiHandler() {
        throw new IllegalStateException("This class should not be instantiated!");
    }

    /**
     * Transforms some classes in the Spoonacular API client.
     * This uses ByteBuddyAgent and ASM to modify the classes at runtime.
     * <p>
     * These transformations are necessary because the Spoonacular API client
     * does not provide all the necessary functionality to
     * make the API calls that are needed for the application.
     * <p>
     * This method is called during class initialization.
     *
     * @throws UnmodifiableClassException if the classes cannot be modified
     */
    private static void transformClasses() throws UnmodifiableClassException {
        Instrumentation instrumentation = ByteBuddyAgent.install();

        HashMap<String, MethodNode> methodNodes = new HashMap<>();
        ClassFileTransformer classNodeReader = new ClassFileTransformer()
        {
            private int passes = 0;

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                if (className.equals("org/ciaa/mealplanner/utilities/ApiHandler$RecipesApiMod") && passes == 0) {
                    CiaaApplication.LOGGER.debug("Reading RecipesApiMod");

                    ClassNode classNode = new ClassNode();
                    ClassReader reader = new ClassReader(classfileBuffer);
                    reader.accept(classNode, 0);

                    classNode.methods.removeIf(methodNode -> methodNode.name.equals("<clinit>") || methodNode.name.equals("<init>"));

                    methodNodes.put(classNode.methods.get(0).name, classNode.methods.get(0));

                    passes++;
                }
                return classfileBuffer;
            }
        };

        instrumentation.addTransformer(classNodeReader, true);
        instrumentation.retransformClasses(RecipesApiMod.class);
        instrumentation.removeTransformer(classNodeReader);

        ClassFileTransformer recipesResposeTransformer = new ClassFileTransformer()
        {
            private int passes = 0;

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                if (!className.equals("com/spoonacular/client/model/GetRandomRecipes200ResponseRecipesInner"))
                    return classfileBuffer;
                if (passes == 1)
                    CiaaApplication.LOGGER.debug("Transforming GetRandomRecipes200ResponseRecipesInner");

                ClassNode classNode = new ClassNode();
                ClassReader reader = new ClassReader(classfileBuffer);
                reader.accept(classNode, 0);

                InsnList instructions = classNode.methods.stream()
                      .filter(methodNode -> methodNode.name.equals("validateJsonElement"))
                      .findFirst()
                      .orElseThrow()
                      .instructions;

                // NOTE: LineNumberNode 1183 is on array index 152
                // NOTE: LineNumberNode 1186 is on array index 177
                // NOTE: LineNumberNode 1189 is on array index 202
                List<AbstractInsnNode> nodesToRemove = new ArrayList<>();
                // 154 - 158 are the first group of instructions that need to be removed
                for (int i = 154; i <= 158; i++) nodesToRemove.add(instructions.get(i));
                // 161 - 175 are the second group of instructions that need to be removed
                for (int i = 161; i <= 175; i++) nodesToRemove.add(instructions.get(i));
                // 178 - 183 are the third group of instructions that need to be removed
                for (int i = 178; i <= 183; i++) nodesToRemove.add(instructions.get(i));
                // 186 - 200 are the fourth group of instructions that need to be removed
                for (int i = 186; i <= 200; i++) nodesToRemove.add(instructions.get(i));
                // 203 - 208 are the fifth group of instructions that need to be removed
                for (int i = 203; i <= 208; i++) nodesToRemove.add(instructions.get(i));
                // 211 - 225 are the sixth group of instructions that need to be removed
                for (int i = 211; i <= 225; i++) nodesToRemove.add(instructions.get(i));
                nodesToRemove.forEach(instructions::remove);

                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classNode.accept(writer);
                passes++;
                return writer.toByteArray();
            }
        };

        instrumentation.addTransformer(recipesResposeTransformer, true);
        instrumentation.retransformClasses(GetRandomRecipes200ResponseRecipesInner.class);
        instrumentation.removeTransformer(recipesResposeTransformer);

        ClassFileTransformer responseTransformer = new ClassFileTransformer()
        {
            private int passes = 0;

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                if (!className.equals("com/spoonacular/client/model/GetRecipeInformation200ResponseExtendedIngredientsInner"))
                    return classfileBuffer;
                if (passes == 1)
                    CiaaApplication.LOGGER.debug("Transforming GetRecipeInformation200ResponseExtendedIngredientsInner");

                ClassNode classNode = new ClassNode();
                ClassReader reader = new ClassReader(classfileBuffer);
                reader.accept(classNode, 0);

                classNode.fields.stream()
                      .filter(fieldNode -> fieldNode.name.equals("SERIALIZED_NAME_CONSITENCY"))
                      .findFirst()
                      .orElseThrow().value = "consistency";

                //noinspection CodeBlock2Expr
                classNode.methods.stream()
                      .filter(methodNode -> methodNode.name.equals("<clinit>") || methodNode.name.equals("validateJsonElement"))
                      .forEach(methodNode -> {
                          methodNode.instructions.forEach(insnNode -> {
                              if (insnNode instanceof LdcInsnNode ldcInsnNode) {
                                  if (ldcInsnNode.cst.equals("consitency")) ldcInsnNode.cst = "consistency";
                              }
                          });
                      });

                InsnList instructions = classNode.methods.stream()
                      .filter(methodNode -> methodNode.name.equals("validateJsonElement"))
                      .findFirst()
                      .orElseThrow()
                      .instructions;

                // NOTE: LineNumberNode 440 is on array index 128
                // NOTE: LineNumberNode 446 is on array index 177
                ArrayList<AbstractInsnNode> nodesToRemove = new ArrayList<>();
                // 129 - 133 are the first group of instructions that need to be removed
                for (int i = 129; i <= 133; i++) nodesToRemove.add(instructions.get(i));
                // 136 - 150 are the second group of instructions that need to be removed
                for (int i = 136; i <= 150; i++) nodesToRemove.add(instructions.get(i));
                // 178 - 183 are the third group of instructions that need to be removed
                for (int i = 178; i <= 183; i++) nodesToRemove.add(instructions.get(i));
                // 186 - 200 are the fourth group of instructions that need to be removed
                for (int i = 186; i <= 200; i++) nodesToRemove.add(instructions.get(i));
                nodesToRemove.forEach(instructions::remove);

                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classNode.accept(writer);
                passes++;
                return writer.toByteArray();
            }
        };

        instrumentation.addTransformer(responseTransformer, true);
        instrumentation.retransformClasses(GetRecipeInformation200ResponseExtendedIngredientsInner.class);
        instrumentation.removeTransformer(responseTransformer);

        ClassFileTransformer recipesApiTransformer = new ClassFileTransformer()
        {
            private int passes = 0;

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                if (!className.equals("com/spoonacular/RecipesApi")) return classfileBuffer;
                if (passes == 1) CiaaApplication.LOGGER.debug("Transforming RecipesApi");

                ClassNode classNode = new ClassNode();
                ClassReader reader = new ClassReader(classfileBuffer);
                reader.accept(classNode, 0);

                MethodNode newMethod = methodNodes.get("splitAndAddParams");
                classNode.methods.add(newMethod);

                InsnList instructions = classNode.methods.stream()
                      .filter(methodNode -> methodNode.name.equals("getRandomRecipesCall"))
                      .findFirst()
                      .orElseThrow()
                      .instructions;

                // NOTE: LineNumberNode 1679 is on array index 98.
                // This assumes that the instruction array is going to be the same every time it is parsed;
                // I sure hope it is.
                // I only need to modify 4 instructions to make this work the way I need it to.
                AbstractInsnNode[] instructionsArray = new AbstractInsnNode[]{
                      // "tags" LDC instruction
                      instructions.get(102),
                      // com/spoonacular/client/ApiClient.parameterToPair INVOKEVIRTUAL instruction
                      instructions.get(104),
                      // java/util/List.addAll INVOKEINTERFACE instruction
                      instructions.get(105),
                      // POP instruction
                      instructions.get(106)
                };

                instructions.set(instructionsArray[1], new MethodInsnNode(
                      Opcodes.INVOKESTATIC,
                      classNode.name,
                      newMethod.name,
                      newMethod.desc,
                      false
                ));

                instructions.remove(instructionsArray[0]);
                instructions.remove(instructionsArray[2]);
                instructions.remove(instructionsArray[3]);

                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classNode.accept(writer);
                passes++;
                return writer.toByteArray();
            }
        };

        instrumentation.addTransformer(recipesApiTransformer, true);
        instrumentation.retransformClasses(RecipesApi.class);
        instrumentation.removeTransformer(recipesApiTransformer);
    }

    /**
     * Tweaks the expected fields of the response classes.
     * These tweaks are necessary because the Spoonacular API client
     * does not initialize these classes with the correct fields.
     * <p>
     * This method is called during class initialization.
     */
    private static void tweakResponses() {
        GetRandomRecipes200ResponseRecipesInner.openapiFields.add("preparationMinutes");
        GetRandomRecipes200ResponseRecipesInner.openapiFields.add("cookingMinutes");
        GetRandomRecipes200ResponseRecipesInner.openapiFields.add("originalId");
        GetRandomRecipes200ResponseRecipesInner.openapiFields.add("author");
        GetRandomRecipes200ResponseRecipesInner.openapiRequiredFields.remove("whole30");
        GetRandomRecipes200ResponseRecipesInner.openapiRequiredFields.remove("ketogenic");
        GetRandomRecipes200ResponseRecipesInner.openapiRequiredFields.remove("license");
        GetRandomRecipes200ResponseRecipesInner.openapiRequiredFields.remove("imageType");
        GetRandomRecipes200ResponseRecipesInner.openapiRequiredFields.remove("image");

        GetRecipeInformation200ResponseExtendedIngredientsInner.openapiFields.add("nameClean");
    }

    /**
     * Authenticates the Spoonacular API client.
     * This sets the API key for the client.
     * <p>
     * Should only be called once, but it has a check to prevent multiple authentications.
     */
    public static void authenticateApiClient() {
        if (!authenticated) {
            ApiKeyAuth authentication = (ApiKeyAuth) CLIENT.getAuthentication("apiKeyScheme");
            authentication.setApiKey("4dc6eb4bd1ec453dbba335faac5055d8");
            authenticated = true;
            return;
        }
        CiaaApplication.LOGGER.warn("API already authenticated! Is there an extra call to authenticateApiClient()?");
    }

    /**
     * Handles the random recipe suggestion API call for the passed user.
     *
     * @param user        the user for whom to suggest meals
     * @param includeTags the tags to include in the suggestion
     * @return a GetRandomRecipes200Response object containing the suggested meals
     */
    public static GetRandomRecipes200Response suggestMeals(User user, String includeTags) {
        RecipesApi api = new RecipesApi(CLIENT);

        StringBuilder builder = new StringBuilder();
        builder.append(includeTags);
        builder.append("&");
        ArrayList<String> userIntolerances = user.getIntolerances();
        if (!userIntolerances.isEmpty()) {
            user.getIntolerances().forEach(intolerance -> builder.append(intolerance).append(","));
            builder.deleteCharAt(builder.length() - 1);
        }

        String intolerances = builder.toString();

        GetRandomRecipes200Response randomRecipes;
        try {
            randomRecipes = api.getRandomRecipes(false, intolerances, 10);
            CiaaApplication.LOGGER.debug("Random recipes: " + randomRecipes.toString());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        return randomRecipes;
    }

    /**
     * A utility class used to modify the RecipesApi class.
     * <p>
     * Since the {@link com.spoonacular.RecipesApi#getRandomRecipesCall(Boolean, String, Integer, ApiCallback)} method
     * expects a single tag, whereas the Spoonacular API itself expects two tags, a string split is necessary to pass
     * the correct parameters to the API.
     */
    private static final class RecipesApiMod
    {
        /**
         * @hidden
         */
        private RecipesApiMod() {
            throw new IllegalStateException("This class should not be instantiated!");
        }

        /**
         * Splits the passed string into two tags and adds them as query parameters.
         * <p>
         * This method is injected into the RecipesApi class and is not meant to be called directly by this application.
         *
         * @param localVarQueryParams the list of query parameters to which the tags will be added
         * @param localVarApiClient   the API client
         * @param string              the string to split
         */
        @SuppressWarnings("unused")
        private static void splitAndAddParams(List<Pair> localVarQueryParams, ApiClient localVarApiClient,
                                              String string) {
            String[] tagArray = string.split("&");

            if (!tagArray[0].isEmpty()) {
                localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-tags", tagArray[0]));
            }

            if (!tagArray[1].isEmpty()) {
                localVarQueryParams.addAll(localVarApiClient.parameterToPair("exclude-tags", tagArray[1]));
            }
        }
    }
}
